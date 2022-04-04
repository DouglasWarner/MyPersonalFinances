package com.douglas.mypersonalfinances.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.douglas.mypersonalfinances.databinding.FragmentMainoverviewBinding
import com.douglas.mypersonalfinances.ui.holders.MonthOverviewHolder
import com.douglas.mypersonalfinances.utils.toMonthPlannedAdapterItem

class MainOverviewFragment : Fragment() {

    private val mainOverviewViewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentMainoverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentMainoverviewBinding.inflate(layoutInflater).apply {
            binding = this
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initRecycler()

        binding.imgBtnYearLeft.setOnClickListener {
            mainOverviewViewModel.lastYear()
        }
        binding.imgBtnYearRight.setOnClickListener {
            mainOverviewViewModel.nextYear()
        }

        mainOverviewViewModel.displayDateResult.observe(viewLifecycleOwner) { date ->
            binding.tvYear.text = date.year().asText
            mainOverviewViewModel.loadData()
        }

        mainOverviewViewModel.allPlannedMonthData.observe(viewLifecycleOwner) { allPlanned ->
            if (allPlanned.isNotEmpty()) {
                MonthOverviewHolder(binding.currentMonth).bindPlanned(allPlanned.toMonthPlannedAdapterItem())
            }
        }
        mainOverviewViewModel.monthOverviewResult.observe(viewLifecycleOwner) { monthResult ->
            monthResult?.let { result ->
                result.monthData?.let { data ->
                    MonthOverviewHolder(binding.currentMonth).bindData(data = data)
//                    (binding.rvMonthOverview.adapter as MonthOverviewAdapter).reloadData(data)
                }
            }
        }

    }

/*
    private fun initRecycler() {
        var snapHelper = LinearSnapHelper()

        binding.rvMonthOverview.apply {
            adapter = MonthOverviewAdapter(context)
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            snapHelper.attachToRecyclerView(this)
        }
    }
*/

    override fun onResume() {
        super.onResume()

//        mainOverviewViewModel.loadData()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}