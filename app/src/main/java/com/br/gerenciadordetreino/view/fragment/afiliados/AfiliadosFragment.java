package com.br.gerenciadordetreino.view.fragment.afiliados;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.br.gerenciadordetreino.R;
import com.br.gerenciadordetreino.model.Cliente;
import com.wenchao.cardstack.CardStack;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

@EFragment(R.layout.fragment_afiliados)
public class AfiliadosFragment extends Fragment {

    @ViewById (R.id.container)
    CardStack mCardStack;
    private CardsDataAdapter mCardAdapter;

    public static AfiliadosFragment newInstance() {
        Bundle args = new Bundle();
        AfiliadosFragment fragment = new AfiliadosFragment_();
        fragment.setArguments(args);
        return fragment;
    }

    @AfterViews
    void initView() {
        mCardStack.setContentResource(R.layout.card_layout);
        mCardStack.setStackMargin(20);
        mCardAdapter = new CardsDataAdapter(getActivity(),R.layout.card_layout);
        mCardAdapter.add(new Cliente(0,"Stark 360", "61 9 98125199", new Date(), 0f,0f,""));
        mCardAdapter.add(new Cliente(0,"Academia de sandiego", "61 9 98125199", new Date(), 0f,0f,""));
        mCardAdapter.add(new Cliente(0,"MARACANÃ", "61 9 98125199", new Date(), 0f,0f,""));
        mCardAdapter.add(new Cliente(0,"Rodrigo maria", "61 9 98125199", new Date(), 0f,0f,""));
        mCardAdapter.add(new Cliente(0,"Body Fithnes", "61 9 98125199", new Date(), 0f,0f,""));
        mCardAdapter.add(new Cliente(0,"Dados mocados", "61 9 98125199", new Date(), 0f,0f,""));

        mCardStack.setAdapter(mCardAdapter);
        mCardStack.reset(true);
        mCardStack.setListener(new CardStack.CardEventListener() {
            @Override
            public boolean swipeEnd(int section, float distance) {
                return (distance>200)? true : false;
            }

            @Override
            public boolean swipeStart(int section, float distance) {
                return true;
            }

            @Override
            public boolean swipeContinue(int section, float distanceX, float distanceY) {
                return true;
            }

            @Override
            public void discarded(int mIndex, int direction) {
                if (mIndex == mCardAdapter.getCount() ){
                    startFragment(AfiliadosFragment_.newInstance());

                }

            }

            @Override
            public void topCardTapped() {

            }
        });

    }
    private void startFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.body_home_empty, fragment);
        fragmentTransaction.commit();
    }
}
