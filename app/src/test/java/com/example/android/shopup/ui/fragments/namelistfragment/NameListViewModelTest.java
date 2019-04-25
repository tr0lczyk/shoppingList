package com.example.android.shopup.ui.fragments.namelistfragment;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.android.shopup.database.repository.ShopUpDatabase;
import com.example.android.shopup.database.repository.ShoppingListsRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class NameListViewModelTest {

    private Context context;
    private Application application;
    private NameListViewModel subject;
    private ShoppingListsRepository shoppingListsRepository;
    private ShopUpDatabase shopUpDatabase;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getInstrumentation().getContext();
        shopUpDatabase = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                ShopUpDatabase.class).build();
        application = ApplicationProvider.getApplicationContext();
        shoppingListsRepository = new ShoppingListsRepository(application);
        subject = new NameListViewModel(application);
    }

    @Test
    public void setName() {
        subject.setName("sampleName");
        assertEquals("sampleName", subject.createListName.get());
    }
}