package Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pennywise.pw.client.model.Expense;
import com.example.myapplication.R; // Make sure R is imported correctly
import java.util.List;
import java.util.Locale;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private static final String ADAPTER_TAG = "ExpenseAdapter";
    private List<Expense> expenseList;
    private OnExpenseDeleteListener deleteListener;

    public interface OnExpenseDeleteListener {
        void onExpenseDelete(Expense expense);
    }

    public ExpenseAdapter(List<Expense> expenseList, OnExpenseDeleteListener deleteListener) {
        this.expenseList = expenseList;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(ADAPTER_TAG, "onCreateViewHolder called");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_expense, parent, false);
        return new ExpenseViewHolder(itemView, deleteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Log.d(ADAPTER_TAG, "onBindViewHolder called for position: " + position);
        Expense currentExpense = expenseList.get(position);
        if (currentExpense != null) {
            Log.d(ADAPTER_TAG, "Binding expense: " + currentExpense.getTitle());
            holder.bind(currentExpense);
        } else {
            Log.w(ADAPTER_TAG, "Expense object is null at position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        int count = expenseList == null ? 0 : expenseList.size();
        Log.d(ADAPTER_TAG, "getItemCount called, returning: " + count);
        return count;
    }

    public void updateExpenses(List<Expense> newExpenses) {
        Log.d(ADAPTER_TAG, "updateExpenses called with " + (newExpenses == null ? "null" : newExpenses.size() + " items."));
        this.expenseList = newExpenses;
        notifyDataSetChanged();
    }

    static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView categoryTextView;
        TextView amountTextView;
        ImageButton deleteButton;
        Expense currentExpense;

        ExpenseViewHolder(View view, final OnExpenseDeleteListener deleteListener) {
            super(view);
            titleTextView = view.findViewById(R.id.item_expense_title);
            categoryTextView = view.findViewById(R.id.item_expense_category);
            amountTextView = view.findViewById(R.id.item_expense_amount);
            deleteButton = view.findViewById(R.id.button_delete_expense);

            deleteButton.setOnClickListener(v -> {
                if (deleteListener != null && currentExpense != null) {
                    deleteListener.onExpenseDelete(currentExpense);
                }
            });
        }

        void bind(Expense expense) {
            this.currentExpense = expense;
            titleTextView.setText(expense.getTitle());
            categoryTextView.setText(expense.getCategory().name());
            amountTextView.setText(String.format(Locale.getDefault(), "$%.2f", expense.getAmount()));
        }
    }
} 