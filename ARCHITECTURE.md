# アーキテクチャ設計書

## MVVM + Clean Architecture

このプロジェクトはMVVM (Model-View-ViewModel) パターンとClean Architectureの原則に基づいています。

## レイヤー構成

```
┌──────────────────────────────────────┐
│   Presentation      │  (UI、ViewModels)
│   Layer             │  Jetpack Compose
└──────────────────┬──────────────────┘
           │
┌──────────────────▼──────────────────┐
│   Domain            │  (UseCase、Entity)
│   Layer             │  ビジネスロジック
└──────────────────┬──────────────────┘
           │
┌──────────────────▼──────────────────┐
│   Data              │  (Repository、Source)
│   Layer             │  Room DB、API
└──────────────────────────────────────┘
```

## 各レイヤーの役割

### Presentation Layer (UI層)
**責務**: ユーザーインタフェース、状態管理

- **Screens**: Jetpack Composeで実装されたUI
- **ViewModels**: UIロジックと状態管理
- **Components**: 再利用可能なComposable

**依存関係**: Domain層のUseCaseに依存

### Domain Layer (ドメイン層)
**責務**: ビジネスロジック、独立した処理

- **UseCase**: 特定のビジネス操作（例：`CalculateNutrientUseCase`）
- **Entity**: アプリケーション内部のモデル
- **Repository Interface**: データアクセスの抽象化
- **Service**: 外部サービスの統合（栄養計算、AI処理）

**依存関係**: 他のレイヤーに依存しない（独立）

### Data Layer (データ層)
**責務**: データの取得・保存、外部API連携

- **Repository Implementation**: UseCaseのRepository実装
- **Local DataSource**: Room DBとのやり取り
- **Remote DataSource**: 外部API（USDA、Claude）との通信
- **Model**: APIレスポンスのデータクラス

**依存関係**: Domain層のRepositoryインターフェースに依存

## データフロー

### 食事記録から栄養素計算までのフロー

```
1. UI (MealInputScreen)
   ↓ ユーザーが食事を入力
2. ViewModel (NutritionViewModel)
   ↓ UseCase呼び出し
3. UseCase (CalculateNutrientUseCase)
   ↓ リポジトリからデータ取得
4. Repository (NutritionRepository)
   ↓ ローカルDB/APIから栄養データ取得
5. Service (NutritionCalculator)
   ↓ 栄養素を計算
6. ViewModel
   ↓ UI状態を更新
7. UI
   ↓ 結果を表示
```

## 依存性注入 (Hilt)

```kotlin
// Hilt Moduleで依存関係を定義
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providesNutritionDatabase(context: Context): NutritionDatabase {
        return Room.databaseBuilder(
            context,
            NutritionDatabase::class.java,
            "nutrition_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesNutritionRepository(
        database: NutritionDatabase
    ): NutritionRepository {
        return NutritionRepositoryImpl(database.mealDao())
    }
}
```

## ファイル構成のベストプラクティス

### 命名規則
- **Screens**: `*Screen.kt` (例: `HomeScreen.kt`)
- **ViewModels**: `*ViewModel.kt` (例: `NutritionViewModel.kt`)
- **Repositories**: `*Repository.kt` (例: `NutritionRepository.kt`)
- **UseCases**: `*UseCase.kt` (例: `CalculateNutrientUseCase.kt`)
- **Services**: `*Service.kt` (例: `NutritionCalculator.kt`)

### パッケージ構成
```
com.example.nutrition
├── ui
│   ├── screens
│   ├── components
│   ├── theme
│   └── navigation
├── viewmodel
├── data
│   ├── model
│   ├── repository
│   ├── local
│   └── remote
├── domain
│   ├── usecase
│   ├── entity
│   └── service
└── utils
```

## テスト戦略

### Unit Tests (ドメイン層)
```kotlin
// ビジネスロジックのテスト
@Test
fun calculateTotalProteins_validMeals_returnsCorrectValue() {
    val meals = listOf(
        Meal("チキン", mapOf("protein" to 25.0)),
        Meal("玉子", mapOf("protein" to 6.0))
    )
    val total = NutritionCalculator.calculateNutrient(meals, "protein")
    assertEquals(31.0, total)
}
```

### Integration Tests (Repository層)
```kotlin
// データ層のテスト
@Test
fun getMealFromDB_savedMeal_returnsCorrectData() {
    // DBにデータを保存
    repository.saveMeal(testMeal)
    
    // DBから取得
    val retrieved = repository.getMeal(testMeal.id)
    
    // 検証
    assertEquals(testMeal, retrieved)
}
```

### UI Tests (Presentation層)
```kotlin
// Compose UIのテスト
@Test
fun mealInputScreen_validInput_callsViewModel() {
    composeTestRule.setContent {
        MealInputScreen(viewModel)
    }
    
    composeTestRule.onNodeWithTag("meal_input").performTextInput("チキン")
    composeTestRule.onNodeWithTag("submit_btn").performClick()
    
    verify(viewModel).addMeal("チキン")
}
```

## パフォーマンス考慮事項

- **Coroutines**: 重い計算はデフォルトディスパッチャーで実行
- **Flow**: リアルタイム統計更新にはFlowを使用
- **Room**: インデックス設定で高速クエリを実現
- **Compose**: `remember`, `derivedStateOf` で不要な再描画を回避

## セキュリティ

- **APIキー**: `local.properties` で管理（Gitにはコミットしない）
- **ユーザーデータ**: EncryptedSharedPreferencesで保存
- **通信**: HTTPS必須

## 今後の拡張計画

### 新機能の追加フロー

1. **Domain層**: UseCase追加 (ビジネスロジック)
2. **Data層**: Repository実装追加 (データ取得)
3. **UI層**: ViewModel追加 (状態管理)
4. **UI層**: Screen作成 (画面表示)
