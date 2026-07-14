# 栄養管理アプリ (Nutrition Management App)

## 概要
Android向けの統合栄養管理アプリケーション。食事内容から栄養素を自動計算し、ユーザーの身長・体重・トレーニング内容・ボディメイク目的に合わせた個別の食事アドバイスを提供します。

## 主な機能

### 📊 栄養管理
- **食事記録**: 食べた内容を入力・検索
- **栄養素自動計算**: タンパク質、炭水化物、脂質、ビタミン、ミネラル等を自動集計
- **日別・週別・月別サマリー**: 栄養摂取状況の可視化

### 👤 ユーザーカスタマイズ
- **個人情報管理**: 身長、体重、年齢、性別の登録
- **トレーニング情報**: 運動内容・強度の記録
- **ボディメイク目的**: 増量/減量/維持、筋力強化、ダイエット等

### 🤖 AI栄養アドバイス
- **個別食事アドバイス**: ユーザー目的に合わせた推奨食事内容
- **栄養バランス提案**: 不足している栄養素の補給提案
- **トレーニング連動**: 運動内容に基づいたタンパク質・炭水化物摂取量の最適化

## 技術スタック

| 項目 | 技術 |
|------|------|
| **言語** | Kotlin |
| **UI Framework** | Jetpack Compose |
| **アーキテクチャ** | MVVM + Clean Architecture |
| **ローカルDB** | Room |
| **依存性注入** | Hilt |
| **非同期処理** | Coroutines + Flow |
| **栄養データ** | USDA FoodData Central API |
| **AI/アドバイス** | Claude API (Anthropic) |

## プロジェクト構成

```
app/
├── src/main/
│   ├── java/com/example/nutrition/
│   │   ├── ui/
│   │   │   ├── screens/
│   │   │   │   ├── HomeScreen.kt
│   │   │   │   ├── MealInputScreen.kt
│   │   │   │   ├── UserProfileScreen.kt
│   │   │   │   ├── AdviceScreen.kt
│   │   │   │   └── StatisticsScreen.kt
│   │   │   ├── components/
│   │   │   └── theme/
│   │   ├── viewmodel/
│   │   │   ├── NutritionViewModel.kt
│   │   │   ├── UserViewModel.kt
│   │   │   └── AdviceViewModel.kt
│   │   ├── data/
│   │   │   ├── model/
│   │   │   │   ├── User.kt
│   │   │   │   ├── Meal.kt
│   │   │   │   ├── Nutrient.kt
│   │   │   │   └── TrainingData.kt
│   │   │   ├── repository/
│   │   │   │   ├── NutritionRepository.kt
│   │   │   │   ├── UserRepository.kt
│   │   │   │   └── FoodRepository.kt
│   │   │   └── local/
│   │   │       └── NutritionDatabase.kt
│   │   ├── domain/
│   │   │   ├── usecase/
│   │   │   │   ├── CalculateNutrientUseCase.kt
│   │   │   │   ├── GenerateAdviceUseCase.kt
│   │   │   │   └── GetStatisticsUseCase.kt
│   │   │   └── service/
│   │   │       ├── NutritionCalculator.kt
│   │   │       ├── AdviceGenerator.kt
│   │   │       └── FoodDataService.kt
│   │   ├── utils/
│   │   │   ├── Constants.kt
│   │   │   ├── DateUtils.kt
│   │   │   └── NutrientFormatter.kt
│   │   └── MainActivity.kt
│   └── res/
│       ├── values/
│       │   ├── colors.xml
│       │   ├── strings.xml
│       │   └── themes.xml
│       └── drawable/
├── build.gradle
└── AndroidManifest.xml

build.gradle (プロジェクトレベル)
settings.gradle
gradle.properties
```

## セットアップガイド

### 前提条件
- Android Studio Flamingo以上
- JDK 11以上
- Android SDK 33以上

### インストール手順

1. **リポジトリのクローン**
```bash
git clone https://github.com/megane98y5m31d-cell/-Ver1.2.git
cd -Ver1.2
```

2. **依存関係のインストール**
```bash
./gradlew build
```

3. **API キーの設定**
- `local.properties` に以下を追加:
```properties
claude.api.key=YOUR_CLAUDE_API_KEY
fooddata.api.key=YOUR_USDA_API_KEY
```

4. **アプリの実行**
```bash
./gradlew installDebug
```

## アーキテクチャ

詳細は `ARCHITECTURE.md` を参照

## 開発ロードマップ

### Phase 1: MVP (v1.0)
- [x] プロジェクト初期化
- [ ] ユーザー登録・ログイン
- [ ] 食事記録機能
- [ ] 栄養素計算エンジン
- [ ] 基本的なUI

### Phase 2: AI統合 (v1.1)
- [ ] Claude API統合
- [ ] AIアドバイス機能
- [ ] 個別推奨栄養素計算

### Phase 3: 拡張機能 (v1.2+)
- [ ] トレーニング連動
- [ ] 統計・グラフ表示
- [ ] ソーシャル機能
- [ ] ウェアラブル連携

## ライセンス
MIT License

## 貢献
プルリクエスト歓迎です。大きな変更の場合は、まずissueを開いて変更内容を議論してください。

## サポート
問題が発生した場合は、GitHubのissuesを参照してください。
