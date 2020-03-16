package com.example.mydialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ListItemHolder> {
    private List<Note> noteList;
    private MainActivity mainActivity;


    public NoteAdapter(MainActivity mainActivity, List<Note> noteList){
        this.mainActivity = mainActivity;
        this.noteList = noteList;

    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new ListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        Note note = noteList.get(position);
        holder.title.setText(note.getTitle());

        if (note.getDescription().length() > 15){
            holder.description.setText(note.getDescription().substring(0,15));
        } else{
            holder.description.setText(note.getDescription());
        }

        String statusText = "";
        if(note.isIdea()){
            statusText += mainActivity.getString(R.string.idea_text) + "";
        }
        if (note.isTodo()){
            statusText += mainActivity.getString(R.string.todo_text) + "";
        }
        if (note.isImportant()){
            statusText += mainActivity.getString(R.string.important_text) + "";
        }
        holder.status.setText(statusText);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ListItemHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            status = itemView.findViewById(R.id.textViewStatus);
            description = itemView.findViewById(R.id.textViewDescription);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mainActivity.showNote(getAdapterPosition());
        }
        TextView title;
        TextView status;
        TextView description;
    }


}
