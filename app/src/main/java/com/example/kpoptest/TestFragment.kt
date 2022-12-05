package com.example.kpoptest

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toolbar.LayoutParams
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.marginEnd
import com.example.kpoptest.R.style.Theme_KPopTest
import com.example.kpoptest.model.Test
import kotlinx.android.synthetic.main.fragment_test.*
import org.w3c.dom.Text

class TestFragment : Fragment() {
    companion object {
        lateinit var test: Test

        fun newInstance(test: Test): TestFragment {
            val fragment = TestFragment()
            this.test = test

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val drawable = "drawable/"

        val tvTitle = TextView(requireContext().applicationContext, null, Theme_KPopTest)
        tvTitle.text = test.title
        tvTitle.gravity = Gravity.CENTER
        tvTitle.textSize = 20.0f

        testLinearlayout.addView(tvTitle)

        var counter = 1
        for(question in test.questions) {
            val tvQuestionCounter = TextView(requireContext().applicationContext, null, Theme_KPopTest)
            tvQuestionCounter.text = "Вопрос " + counter.toString()
            tvQuestionCounter.textSize = 16.0f


            val separator = View(requireContext().applicationContext)
            separator.setBackgroundColor(ContextCompat.getColor(requireContext().applicationContext, R.color.black))
            separator.layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, 1)

            val image = ImageView(requireContext().applicationContext)
            image.layoutParams = ViewGroup.LayoutParams(800, 800)
            image.setImageResource(resources.getIdentifier(drawable + question.image, "drawable", activity?.packageName))

            val tvQuestion = TextView(requireContext().applicationContext, null, Theme_KPopTest)
            tvQuestion.text = question.question

            val answer1 = RadioButton(requireContext().applicationContext)
            answer1.text = question.answers[0].answer
            if(question.answers[0].isCorrect) {
                answer1.tag = "correct"
            }

            val answer2 = RadioButton(requireContext().applicationContext)
            answer2.text = question.answers[1].answer
            if(question.answers[1].isCorrect) {
                answer2.tag = "correct"
            }

            val answer3 = RadioButton(requireContext().applicationContext)
            answer3.text = question.answers[2].answer
            if(question.answers[2].isCorrect) {
                answer3.tag = "correct"
            }

            val answer4 = RadioButton(requireContext().applicationContext)
            answer4.text = question.answers[3].answer
            if(question.answers[3].isCorrect) {
                answer4.tag = "correct"
            }

            val radioGroup = RadioGroup(requireContext().applicationContext)
            radioGroup.addView(answer1)
            radioGroup.addView(answer2)
            radioGroup.addView(answer3)
            radioGroup.addView(answer4)

            testLinearlayout.addView(tvQuestionCounter)
            testLinearlayout.addView(separator)
            testLinearlayout.addView(image)
            testLinearlayout.addView(tvQuestion)
            testLinearlayout.addView(radioGroup)

            counter++
        }

        val btnFinish = Button(requireContext().applicationContext)
        btnFinish.setBackgroundColor(resources.getColor(R.color.primary))
        btnFinish.text = "Завершить тест"
        btnFinish.setTextColor(resources.getColor(R.color.text_color))

        testLinearlayout.addView(btnFinish)

        btnFinish.setOnClickListener {
            var correctAnswers = 0
            for (view in testLinearlayout.children) {
                if (view is RadioGroup)
                    for (radioButton in view.children)
                        if(radioButton.tag == "correct" && (radioButton as RadioButton).isChecked)
                            correctAnswers++
            }

            val builder = AlertDialog.Builder(activity)

            builder.setTitle("Результат")
                .setMessage("Ваш результат: $correctAnswers из ${test.questions.size}")
                .setPositiveButton("ОК") { _, _ ->
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.hostFragment, MainFragment())
                        ?.commit()
                }.show()
        }
    }
}