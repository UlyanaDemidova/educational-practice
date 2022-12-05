package com.example.kpoptest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kpoptest.singleton.Singleton
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tests = Singleton.getTests(requireContext().applicationContext)

        btnNicknamesTest.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.hostFragment, TestFragment.newInstance(tests[0]))
                ?.commit()
        }

        btnClipTest.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.hostFragment, TestFragment.newInstance(tests[1]))
                ?.commit()
        }

        btnChildPhotoTest.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.hostFragment, TestFragment.newInstance(tests[2]))
                ?.commit()
        }

        btnMemberTest.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.hostFragment, TestFragment.newInstance(tests[3]))
                ?.commit()
        }

        btnBiographyTest.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.hostFragment, TestFragment.newInstance(tests[4]))
                ?.commit()
        }

    }
}