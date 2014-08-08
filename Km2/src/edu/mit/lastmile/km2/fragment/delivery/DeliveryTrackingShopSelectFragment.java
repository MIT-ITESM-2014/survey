package edu.mit.lastmile.km2.fragment.delivery;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardGridView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

import edu.mit.lastmile.km2.R;
import edu.mit.lastmile.km2.activity.DeliveryTrackingShopActivity;
import edu.mit.lastmile.km2.adapter.DeliveryTrackingShopGridAdapter;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.ListView;

@EFragment(R.layout.fragment_delivery_tracking_shop_select)
public class DeliveryTrackingShopSelectFragment extends Fragment {

	private ArrayList<Card> cards;
	
	@ColorRes
	protected int deliveryTracking;
	
	@ViewById
	protected Button continueBtn;
	
	@ViewById
	protected CardGridView shopGrid;
	
	@AfterInject
	protected void init(){
		initActionBar();
		cards = new ArrayList<Card>();

		//Create a Card
		Card card = new Card(getActivity());
	
		//Create a CardHeader
		CardHeader header = new CardHeader(getActivity());

		//Add Header to card
		card.addCardHeader(header);
		card.setTitle("Card Header");
		
		for(int i = 0; i < 5; ++i){
			cards.add(card);
		}
	}
	
	private void initActionBar(){
		ColorDrawable c = new ColorDrawable(deliveryTracking);
		getActivity().getActionBar().setBackgroundDrawable(c);		
	}
	
	@AfterViews
	protected void initViews(){
		DeliveryTrackingShopGridAdapter adapter = new DeliveryTrackingShopGridAdapter(getActivity(), cards);
		shopGrid.setAdapter(adapter);
		shopGrid.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
	}
	
	@Click
	protected void continueBtn(){
		((DeliveryTrackingShopActivity) getActivity()).replaceFragment(new DeliveryTrackingShopConfirmationFragment_());
	}
	
}
