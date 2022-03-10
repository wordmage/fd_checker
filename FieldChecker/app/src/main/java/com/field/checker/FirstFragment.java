package com.field.checker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.field.checker.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCheck.setOnClickListener(localView -> {
            String message;

            if (areMessagesTheSame()) {
                message = "SAME";
            } else {
                message = "NOT THE SAME";
            }

            // We need to cast getActivity() as MainActivity because
            // we have to make use of the inter-fragment message.
            ((MainActivity) getActivity()).setMessage(message);

            // Switch to our other fragment.
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Compares the contents of the two EditTexts.
    public boolean areMessagesTheSame() {
        return binding.edittextTop.getText().toString().equals(binding.edittextBottom.getText().toString());
    }
}