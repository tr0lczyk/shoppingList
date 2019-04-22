package com.example.android.shopup.database.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.android.shopup.database.dao.ShoppingListDao;
import com.example.android.shopup.models.ShoppingList;

import java.util.List;

public class ShoppingListsRepository extends BaseRepository<ShoppingList> {

    private ShoppingListDao shoppingListDao;
    private List<ShoppingList> shoppingLists;
    private List<ShoppingList> shoppingListsActive;
    private List<ShoppingList> shoppingListsArchived;
    public static final String TAG = "shoppingListsRepository";

    public ShoppingListsRepository(Application application) {
        super(application);
        shoppingListDao = database.getSgoppingListDao();
        shoppingLists = shoppingListDao.getAllItems();
        shoppingListsActive = shoppingListDao.getAllItemsifArchivedOrNot(true);
        shoppingListsArchived = shoppingListDao.getAllItemsifArchivedOrNot(false);
    }

    @Override
    public void insert(ShoppingList object) {
        new InsertAsyncTask(shoppingListDao).execute(object);
    }

    @Override
    public void update(ShoppingList object) {
        new UpdateAsyncTask(shoppingListDao).execute(object);
    }

    @Override
    public void delete(ShoppingList object) {
        new DeleteAsyncTask(shoppingListDao).execute(object);
    }

    @Override
    public void nukeAllObjects() {
        new DeleteAllShoppingItemsAsyncTask(shoppingListDao).execute();
    }

    @Override
    public List<ShoppingList> getAllObjects() {
        return shoppingLists;
    }

    @Override
    public ShoppingList getOneObject(int id) {
        return shoppingListDao.getOneItem(id);
    }

    public List<ShoppingList> getAllActiveLists() {
        return shoppingListsActive;
    }

    public List<ShoppingList> getAllArchivedLists() {
        return shoppingListsArchived;
    }

    private class InsertAsyncTask extends AsyncTask<ShoppingList, Void, Void> {

        private ShoppingListDao shoppingListDao;

        public InsertAsyncTask(ShoppingListDao shoppingListDao) {
            this.shoppingListDao = shoppingListDao;
        }

        @Override
        protected Void doInBackground(ShoppingList... shoppingLists) {
            shoppingListDao.insert(shoppingLists[0]);
            Log.d(TAG,"INSERT_CATEGORY " + shoppingLists[0].name);
            return null;
        }
    }

    private class UpdateAsyncTask extends AsyncTask<ShoppingList, Void, Void> {

        private ShoppingListDao shoppingListDao;

        public UpdateAsyncTask(ShoppingListDao shoppingListDao) {
            this.shoppingListDao = shoppingListDao;
        }

        @Override
        protected Void doInBackground(ShoppingList... shoppingLists) {
            shoppingListDao.update(shoppingLists[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<ShoppingList, Void, Void> {

        private ShoppingListDao shoppingListDao;

        public DeleteAsyncTask(ShoppingListDao shoppingListDao) {
            this.shoppingListDao = shoppingListDao;
        }

        @Override
        protected Void doInBackground(ShoppingList... shoppingLists) {
            shoppingListDao.delete(shoppingLists[0]);
            return null;
        }
    }

    private class DeleteAllShoppingItemsAsyncTask extends AsyncTask<ShoppingList,Void, Void>{

        private ShoppingListDao shoppingListDao;

        public DeleteAllShoppingItemsAsyncTask(ShoppingListDao shoppingListDao) {
            this.shoppingListDao = shoppingListDao;
        }

        @Override
        protected Void doInBackground(ShoppingList... shoppingLists) {
            shoppingListDao.nukeTable();
            return null;
        }
    }
}
