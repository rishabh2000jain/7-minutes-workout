package com.example.a7minuteworkout.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.a7minuteworkout.util.Constants
import com.example.a7minuteworkout.adapters.ExerciseListAdapter
import com.example.a7minuteworkout.models.ExerciseModel
import com.example.a7minuteworkout.application.SevenMinutesApplication
import com.example.a7minuteworkout.databinding.ActivityExerciseBinding
import com.example.a7minuteworkout.db.History
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration
import java.util.Calendar

class ExerciseActivity : AppCompatActivity() {
    lateinit var binding:ActivityExerciseBinding
    private var restCountDownTimer: CountDownTimer? = null
    private var exerciseCountDownTimer: CountDownTimer? = null
    private val restCountDownTimerDuration:Long = 15000L
    private val exerciseCountDownTimerDuration:Long = 30000L
    private var exerciseListAdapter: ExerciseListAdapter? = null
    private val countDownInterval:Long = 1000L
    private var currentExerciseIndex = 0

    private val exerciseList:ArrayList<ExerciseModel> by lazy { Constants.defaultExerciseList() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        if(supportActionBar!=null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        setUpRecyclerView()
        startRestTimer()
    }

    private fun startRestTimer(){
        restCountDownTimer = restCountDownTimer?:object : CountDownTimer(restCountDownTimerDuration,countDownInterval){
            override fun onTick(millisUntilFinished: Long) {
                showRestView()
                binding.restProgressBar.progress = (((millisUntilFinished/restCountDownTimerDuration.toDouble())*100).toInt())
                binding.restProgressTxt.text = Duration.ofMillis(millisUntilFinished).seconds.toString()
            }

            override fun onFinish() {
                hideRestView()
                binding.restProgressBar.progress = 0
                binding.restProgressTxt.text = binding.restProgressBar.progress.toString()
                restCountDownTimer = null
                showExerciseView()
                startExerciseTimer()
            }
        }
        restCountDownTimer?.start()
    }

   private fun startExerciseTimer(){
       setCurrentExercise(currentExerciseIndex)
       exerciseCountDownTimer = exerciseCountDownTimer?:object : CountDownTimer(exerciseCountDownTimerDuration,countDownInterval){
           override fun onTick(millisUntilFinished: Long) {
               showExerciseView()
               binding.exerciseProgressBar.progress = (((millisUntilFinished/exerciseCountDownTimerDuration.toDouble())*100).toInt())
               binding.exerciseProgressTxt.text = Duration.ofMillis(millisUntilFinished).seconds.toString()
           }

           override fun onFinish() {
               setExerciseComplete(currentExerciseIndex)
               currentExerciseIndex++
               hideExerciseView()
               restCountDownTimer = null
               binding.exerciseProgressBar.progress = 0
               binding.exerciseProgressTxt.text = binding.exerciseProgressBar.progress.toString()
               showRestView()
               if(currentExerciseIndex < exerciseList.size) {
                   startRestTimer()
               }else{
                   exerciseFinished()
               }
           }
       }
       exerciseCountDownTimer?.start()
   }


    private fun hideRestView(){
        binding.restProgressBarFrameLayout.visibility = View.INVISIBLE
        binding.restExerciseNameTxt.visibility = View.INVISIBLE
    }
    private fun showRestView(){
        binding.restProgressBarFrameLayout.visibility = View.VISIBLE
        binding.restExerciseNameTxt.visibility = View.VISIBLE
        binding.restExerciseNameTxt.text = ""
        if(currentExerciseIndex < exerciseList.size) {
            binding.restExerciseNameTxt.text = binding.restExerciseNameTxt.text.toString().plus("Get Ready For "+exerciseList[currentExerciseIndex].getName())
        }
    }

    private fun hideExerciseView(){
        binding.exerciseProgressBarFrameLayout.visibility = View.INVISIBLE
        binding.exerciseList.visibility = View.INVISIBLE
        binding.exerciseName.visibility = View.INVISIBLE
        binding.exerciseImageView.visibility = View.INVISIBLE
    }
    private fun showExerciseView(){
        binding.exerciseProgressBarFrameLayout.visibility = View.VISIBLE
        binding.exerciseList.visibility = View.VISIBLE
        binding.exerciseName.visibility = View.VISIBLE
        binding.exerciseImageView.visibility = View.VISIBLE
        binding.exerciseImageView.background = (ContextCompat.getDrawable(this,exerciseList[currentExerciseIndex].getImage()))
        binding.exerciseName.text = exerciseList[currentExerciseIndex].getName()
    }

    private fun setUpRecyclerView(){
        exerciseListAdapter = ExerciseListAdapter(exerciseList)
        binding.exerciseList.adapter = exerciseListAdapter
    }

    private fun setCurrentExercise(index:Int){
        exerciseList[index].setIsSelected(true)
        exerciseListAdapter?.notifyItemChanged(index)
    }
    private fun setExerciseComplete(index:Int){
        exerciseList[index].setIsCompleted(true)
        exerciseListAdapter?.notifyItemChanged(index)
    }

    override fun onDestroy() {
        super.onDestroy()
        exerciseCountDownTimer?.cancel()
        restCountDownTimer?.cancel()
        exerciseCountDownTimer = null
        restCountDownTimer = null
    }
    private fun navigateToFinishActivity(){
        finish()
        val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
        startActivity(intent)
    }


    private suspend fun logDateTimeToDb(){
        val db = (application as SevenMinutesApplication).database
        val calendar = Calendar.getInstance()
        db.getDao().insertHistoryItem(History(date = calendar.time.time))
    }

    private fun exerciseFinished(){
        lifecycleScope.launch(Dispatchers.IO){
            logDateTimeToDb()
        }
        navigateToFinishActivity()
    }
}