package co.eduardo.animapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class AdaptadorPersonajes extends RecyclerView.Adapter<AdaptadorPersonajes.ViewHolderPersonajes>
        implements View.OnClickListener, Filterable {
    ArrayList<PersonajeVo> listaPersonajes;
    private View.OnClickListener listener;
    private int lastPosition = -1;//animacion

    List<PersonajeVo> modellist;
    public AdaptadorPersonajes(ArrayList<PersonajeVo> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
        modellist= new ArrayList<PersonajeVo>();
        modellist.addAll(listaPersonajes);
    }
    @Override
    public ViewHolderPersonajes onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout=0;
        if (Utilidades.visualizacion==Utilidades.LIST){
            layout=R.layout.iem_list_personajes;
        }
        View view= LayoutInflater.from(parent.getContext()).inflate(layout,null,false);
        view.setOnClickListener(this);
        return new ViewHolderPersonajes(view);
    }
    @Override
    public void onBindViewHolder(ViewHolderPersonajes holder, int position) {
        holder.etiNombre.setText(listaPersonajes.get(position).getNombre());
        holder.foto.setImageResource(listaPersonajes.get(position).getFoto());
        //holder.sonido.setImageResource(listaPersonajes.get(position).getSonido());
        setAnimation(holder.itemView, position);
    }
    public void setAnimation(View viewToAnimate, int position){
        if (position > lastPosition){
            ScaleAnimation animation = new ScaleAnimation(0.0f,1.0f,0.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(1500);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }

    }
   //Filter
    @Override
    public Filter getFilter(){
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PersonajeVo> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(modellist);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (PersonajeVo item : modellist){
                    if (item.getNombre().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listaPersonajes.clear();
            listaPersonajes.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public int getItemCount() {
        return listaPersonajes.size();//Retornar el tamaño de la lista
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderPersonajes extends RecyclerView.ViewHolder {
        TextView etiNombre;
        ImageView foto;
        ImageView sonido;

        public ViewHolderPersonajes(View itemView) {
            super(itemView);
            etiNombre= (TextView) itemView.findViewById(R.id.idNombre);
            foto= (ImageView) itemView.findViewById(R.id.idImagen);
            //sonido = (ImageView) itemView.findViewById(R.id.idsonido);
        }
    }
}
