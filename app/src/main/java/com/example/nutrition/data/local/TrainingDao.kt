package com.example.nutrition.data.local

import androidx.room.*
import com.example.nutrition.data.model.TrainingData
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTraining(training: TrainingData)

    @Delete
    suspend fun deleteTraining(training: TrainingData)

    @Query("SELECT * FROM training_data ORDER BY date DESC")
    fun getAllTraining(): Flow<List<TrainingData>>

    @Query("SELECT * FROM training_data WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getTrainingByDateRange(startDate: Long, endDate: Long): Flow<List<TrainingData>>
}
