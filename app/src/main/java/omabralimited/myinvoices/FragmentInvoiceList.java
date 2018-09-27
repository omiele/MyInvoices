package omabralimited.myinvoices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import omabralimited.myinvoices.databases.InvoiceDbHelper;

public class FragmentInvoiceList extends Fragment {
  public static FragmentInvoiceList newInstance() {
    FragmentInvoiceList fragment = new FragmentInvoiceList();
    return fragment;
  }

  public FragmentInvoiceList() {
    // Required empty public constructor
  }
  public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_ITEM_ID";
  //    private ProgressBar pbLoading;
  Context context;
 InvoiceDbHelper dbHelper;
  ListView listView;
  private OnItemSelectedListener onItemSelectedListener;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_voterlist, container, false);
    context = getActivity().getApplicationContext();
    final ListView listview = (ListView) view.findViewById(R.id.LIST_VIEW_VOTERS);
    listview.setVisibility(View.VISIBLE);
    dbHelper = new InvoiceDbHelper(context);
    final StableArrayAdapter adapter2 = new StableArrayAdapter(getActivity(),
            android.R.layout.simple_list_item_1, dbHelper.getAllInvoices());
    listview.setAdapter(adapter2);
    FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getActivity(), SaveInvoiceActivity.class);
        startActivity(intent);
      }
    });
    return view;
  }
  public interface OnItemSelectedListener{
    void invoiceSelected(InvoiceInfo invoiceInfo);
  }
  private class StableArrayAdapter extends ArrayAdapter<InvoiceInfo> {

    private HashMap<InvoiceInfo, Integer> mIdMap = new HashMap<InvoiceInfo, Integer>();
    private final Context context;

    public StableArrayAdapter(Context context, int textViewResourceId,
                              List<InvoiceInfo> objects) {
      super(context, textViewResourceId, objects);
      this.context = context;
      for (int i = 0; i < objects.size(); ++i) {
        mIdMap.put(objects.get(i), i);
      }
    }

    @Override
    public long getItemId(int position) {
      InvoiceInfo voter = getItem(position);
      return mIdMap.get(voter);
    }

    @Override
    public boolean hasStableIds() {
      return true;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      LayoutInflater inflater = (LayoutInflater) context
              .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View rowView = inflater.inflate(R.layout.invoice_row_layout, parent, false);
      ImageView image = (ImageView) rowView.findViewById(R.id.icon12);
      TextView title = (TextView) rowView.findViewById(R.id.title12);
      TextView date = (TextView) rowView.findViewById(R.id.tvdate);
      TextView shopName = (TextView) rowView.findViewById(R.id.tvshopName);
      TextView type = (TextView) rowView.findViewById(R.id.txt_type);
      final InvoiceInfo invoice = getItem(position);
      title.setText(invoice.getTitle());
      date.setText(invoice.getDate());
      shopName.setText(invoice.getShopName());
      type.setText(invoice.getType());
      image.setImageResource(R.drawable.user_avatar);
      return rowView;
    }

  }

}
