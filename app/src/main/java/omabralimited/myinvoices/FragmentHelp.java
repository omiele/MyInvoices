package omabralimited.myinvoices;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

public class FragmentHelp extends Fragment {
  private View myFragmentView;
  private TextView voterCount;
  private TextView deathCount;
  private TextView deadVoterCount;
  int count = 0;
  public static FragmentHelp newInstance() {
    FragmentHelp fragment = new FragmentHelp();

    return fragment;
  }

  public FragmentHelp() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    myFragmentView = inflater.inflate(R.layout.fragment_help, container, false);
    WebView browser = (WebView) myFragmentView.findViewById(R.id.webview);
    browser.loadUrl("https://en.wikipedia.org/wiki/Invoice");
    return myFragmentView;
  }

}
