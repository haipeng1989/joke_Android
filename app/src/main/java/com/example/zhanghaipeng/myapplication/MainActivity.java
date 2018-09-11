package com.example.zhanghaipeng.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private LinkedList<String> mListItems;
    private String[] data = {
            "1�������յ���Ů�Ѽ����������һ���Ƕ�LV��ñ�ӣ���������˼�����ң���LV��������ô˵����\n" +
            "\n" +
            "�������ش𣺡��̡�������",
            "2����Ƶ�·���£����ڳ����ϵ�Ů�����������ӿ���·��������״������ϧ���ߵ�����ǰ�ʵ������㻹�ðɣ���\n" +
                    "\n" +
                    "Ů������޵��滨���������ϰ���Իش�˵�����ҡ�������û�£� ��\n" +
                    "������·�ƿ�����Ů���ĳ��࣬���ǽӵ�����Ŷ��û�����������ˣ� ��",
            "3�����ѽ��Ҹ�����ȥ���������Ϸ��Ը��ȥ������˵�㲻ȥ���ڣ��ұ���������ܶ��������ʶ�ġ�\n" +
                    "\n" +
                    "����ȥ�������⻻���·�����ȥ�˶���԰��ת�˰�Ȧ����˵����ܵĶ����أ�\n" +
                    "���ָ�Ų�Զ��˵�������ǣ�һ�Դ���",
            "4������Ը�⵱��Ů�����𣿡�\n" +
                    "\n" +
                    "������ɡ�\n" +
                    "�����죿��\n" +
                    "���������˼����ί��ܾ����������ѵ�û�˶���˵����������Է�����",
            "5������Ů�Ѹ��Ҵ����绰�����ҷ����ҵ�Ь�Ͼ�Ȼ�ж�����¶����ֺͷ���ˣ� ��\n" +
                    "\n" +
                    "�ң�����������˫�µİɣ� ��\n" +
                    "Ů�ѣ�����ѡ���ˣ��ڹ��ﳵ������Ҹ���ɣ� ��\n" +
                    "�ң����õģ� ��\n" +
                    "�������ң����װ��ģ�Ь�Ѿ����ˣ�����˫¶��ֺͷ��Ь�����˰ɣ� ��\n" +
                    "�����������ӣ�����˫��Ь�����������ڡ���" };

    private String[] data1 = {
            "1��һֻ������·�߷����������Ҿ������������˯�Ļ�è��ʹ����������ȥ��\n" +
                    "\n" +
                    "è�������ס�ˣ������˲���һ�룬����Ťͷ���ܡ�����",
            "2��Сʱ���дκ͸���һ���ȥ�棬��·��������ʮ��Ǯ��˵ʵ�����������Ǽٵģ�����������ȥС��������Щ��ʳ���˰˿�Ǯ��������������Щƽ���ʱ�򣬽���ϰ�������ʮ��������",
            "3����Ů����飺����ǰ�һ��جج��ֱ�������㣬�Ҳ�֪�����Ժ�ø�ʲô����\n" +
                    "\n" +
                    "Ů��ž��һ���ƣ�����å�� ��",
            "4��Ůͬ�´չ���˵���������ε�������߲����������ҡ���\n" +
                    "\n" +
                    "����һ�죺��������\n" +
                    "Ůͬ�£����㻹��������ͷ�ء���\n" +
                    "�ң���������������ô��Ƥô����\n" +
                    "Ůͬ�£����ţ���ʱ��Ͷ��ڶ�����űߡ���\n" +
                    "�ҡ�����",
            "5�����Ұ��ҷ����������� ��\n" +
                    "\n" +
                    "�������ж�ȱǮ������Ҳ���������ⷿ�𣿡�\n" +
                    "���һ��о��ף� ��\n" +
                    "��Ŷ��������" };

    private String[] data2 = {
            "1��һֱ�����Լ�Ц��ͣ�ֱ������ȥ�������˸�������û����ֱ�������Ӱ���һ�������ˣ��ϵ��ݵ�ʱ���Ҹ�Ů��˵��һ�䣬˭�����ң��������и���ζ�������Ǵ�!Ȼ���Ա�һ�����Ӿ͹������������Ŀ�Ц��һ·������\n",
            "2������һ�����룬���Ǵ���ī���������������½����磬����20�����Ŭ�����Ѿ�ʵ��һ���ˣ���ӵ����ī����\n",
            "3��˵�������̵�����!һ��ȥ���⣬���ϰ���ҳ�һ��������Ǯ������20�顱����̫���ˣ���һ�롱�������ϰ���˽�С�ǿ�˵��13�顱����������һ��ɣ�����7��Ǯ��!�Ǽһ����û�ع���!",
            "4����˵����ǰ������ٴλ������Ż��ý�����һ�β��磬�������������ܵ����ѣ��ϱ����ƺ�û��ʲô���������ͷ�ˣ�",
            "5����������ٳ��Ĳ�С�������ֵ15Ԫ�ļٳ������˾����õ�ƫԶɽ����������������һ��15Ԫ����1Ԫ���Ǻ�«�����ǿ��ˣ�ũ��������������7���" };

    private int index = 0;

    private PullToRefreshListView mPullRefreshListView;

    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                MainActivity.this, android.R.layout.simple_list_item_1, data);
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.list_view);
//        mPullRefreshListView.setAdapter(adapter);

        // Set a listener to be invoked when the list should be refreshed.
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

                // Do work to refresh the list here.
                new GetDataTask().execute();
            }
        });

        // Add an end-of-list listener
        mPullRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                Toast.makeText(MainActivity.this, "End of List!", Toast.LENGTH_SHORT).show();
            }
        });

        ListView actualListView = mPullRefreshListView.getRefreshableView();

        // Need to use the Actual ListView when registering for Context Menu
        registerForContextMenu(actualListView);

        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(data));

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);

        actualListView.setAdapter(mAdapter);

        index = 0;
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            return data1;
        }

        @Override
        protected void onPostExecute(String[] result) {
            mAdapter.clear();
            if (index == 0) {
                mAdapter.addAll(data1);
                index++;
            } else if (index == 1) {
                mAdapter.addAll(data2);
                index++;
            }  else if (index == 2) {
                mAdapter.addAll(data);
                index = 0;
            }
            mAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }
}
