package com.douglas.mypersonalfinances.ui.manageMyTransaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.douglas.mypersonalfinances.data.MpfDatabase
import com.douglas.mypersonalfinances.data.model.TypeTransaction
import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction
import com.douglas.mypersonalfinances.databinding.FragmentManageMyTransactionBinding
import com.google.android.material.snackbar.Snackbar
import org.joda.time.LocalDateTime

class ManageMyTransactionFragment : Fragment() {

    private val manageMyTransactionViewModel: ManageMyTransactionViewModel by viewModels()
    private lateinit var binding: FragmentManageMyTransactionBinding
    private var myTransaction: MyTransaction? = null
    private var isEdited: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        FragmentManageMyTransactionBinding.inflate(inflater).apply {
            binding = this
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { extras ->
            myTransaction = extras.getSerializable(MyTransaction.TAG) as MyTransaction?
        }

        myTransaction?.let { transition ->
            binding.tvTransactionDate.text = transition.dateTime.toString()
        }

        binding.btnSaveTransaction.setOnClickListener {

        }

        manageMyTransactionViewModel.myTransactionForm.observe(viewLifecycleOwner) {
            it.categoryError?.let { error ->
                Snackbar.make(view, error, Snackbar.LENGTH_LONG).show()
            }
            it.isDataValid.let { enabled ->
                binding.btnSaveTransaction.isEnabled = enabled
            }
        }

        manageMyTransactionViewModel.myTransactionResult.observe(viewLifecycleOwner) {
            it.success?.let { success ->
                Snackbar.make(view, success, Snackbar.LENGTH_LONG).show()
            }
            it.error?.let { error ->
                Snackbar.make(view, error, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}