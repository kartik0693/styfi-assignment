package com.styfi.assignment;

import com.styfi.assignment.constants.StyfiAssignmentStringConstants;
import com.styfi.assignment.interactor.ManufacturerDataInteractor;
import com.styfi.assignment.model.ManufacturerData;
import com.styfi.assignment.presenter.ManufacturerDataPresenter;
import com.styfi.assignment.view.ManufacturerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Kartik
 */

@RunWith(MockitoJUnitRunner.class)
public class ManufacturerDataPresenterTest {


    @Mock
    ManufacturerView view;

    @Mock
    ManufacturerDataInteractor interactor;

    private ManufacturerDataPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new ManufacturerDataPresenter(view, interactor);
    }


    @Test
    public void checkIfShowsProgressOnSync() {
        presenter.onCreate();
        verify(view, times(1)).showProgress();
    }

    @Test
    public void checkIfShowsMessageOnError() {
        ArgumentCaptor<String> captor = forClass(String.class);
        presenter.onError();
        verify(view, times(1)).showMessage(captor.capture());
        assertThat(captor.getValue(), is(StyfiAssignmentStringConstants.DATA_LOADING_ERROR));
    }

    @Test
    public void checkIfShowsMessageOnEmptyData() {
        ArgumentCaptor<String> captor = forClass(String.class);
        presenter.onEmptyData();
        verify(view, times(1)).showMessage(captor.capture());
        assertThat(captor.getValue(), is(StyfiAssignmentStringConstants.NO_DATA_AVAILABLE));
    }

    @Test
    public void checkIfItemsArePassedToView() {
        List<ManufacturerData> items = new ArrayList<>();

        ManufacturerData model = new ManufacturerData();
        model.setManufacturerID("1");
        model.setManufacturerData("Model");
        ManufacturerData modelView = new ManufacturerData();
        modelView.setManufacturerID("2");
        modelView.setManufacturerData("View");
        ManufacturerData presenter = new ManufacturerData();
        presenter.setManufacturerID("3");
        presenter.setManufacturerData("Presenter");

        items.add(model);
        items.add(modelView);
        items.add(presenter);

        this.presenter.onDataFetched(items);
        verify(view, times(1)).setItems(items);
        verify(view, times(1)).hideProgress();
    }

}