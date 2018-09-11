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
            "1、朋友收到了女友寄来的礼物，打开一看是顶LV的帽子，他若有所思的问我：“LV用中文怎么说？”\n" +
            "\n" +
            "我弱弱地答：“绿。。。”",
            "2、昏黄的路灯下，坐在长椅上的女孩捂着脸嘤嘤哭泣路过的他见状心生怜惜，走到她面前问道：“你还好吧？”\n" +
                    "\n" +
                    "女孩仰起哭得梨花带雨的脸，习惯性回答说：“我。。。我没事！ ”\n" +
                    "他借着路灯看清了女孩的长相，于是接到：“哦，没事那我先走了！ ”",
            "3、室友叫我跟他出去溜溜，我玩游戏不愿意去，室友说你不去别后悔，我本来打算介绍对象给你认识的。\n" +
                    "\n" +
                    "好我去！我特意换了衣服跟他去了动物园，转了半圈后我说你介绍的对象呢？\n" +
                    "这货指着不远处说：就在那，一对大象！",
            "4、“你愿意当我女朋友吗？”\n" +
                    "\n" +
                    "“改天吧”\n" +
                    "“哪天？”\n" +
                    "“改天的意思就是委婉拒绝，不懂？难道没人对你说过改天请你吃饭？”",
            "5、今天女友给我打来电话：“我发现我的鞋上竟然有洞，都露出脚趾头来了！ ”\n" +
                    "\n" +
                    "我：“啊？那买双新的吧！ ”\n" +
                    "女友：“我选好了，在购物车，你帮我付款吧！ ”\n" +
                    "我：“好的！ ”\n" +
                    "付完款后，我：“亲爱的，鞋已经买了，你那双露脚趾头的鞋就扔了吧！ ”\n" +
                    "她：“不能扔，我那双凉鞋才买两个星期。”" };

    private String[] data1 = {
            "1、一只老鼠在路边翻着垃圾，我揪起店里正在熟睡的花猫，使劲向老鼠扔去。\n" +
                    "\n" +
                    "猫和老鼠都愣住了，对视了不到一秒，各自扭头就跑。。。",
            "2、小时侯有次和哥们一起出去玩，在路上他捡了十块钱，说实话不嫉妒那是假的，于是我让他去小卖部买了些零食花了八块钱，就在我心里有些平衡的时候，结果老板找他四十二。。。",
            "3、跟女友求婚：“以前我浑浑噩噩，直到遇到你，我才知道我以后该干什么？”\n" +
                    "\n" +
                    "女友啪地一巴掌：“流氓！ ”",
            "4、女同事凑过来说：“昨晚梦到你驾着七彩祥云来看我。”\n" +
                    "\n" +
                    "我脸一红：“啊？”\n" +
                    "女同事：“你还冲我伸舌头呢。”\n" +
                    "我：“哈哈，我有这么调皮么？”\n" +
                    "女同事：“嗯，当时你就蹲在二郎神脚边。”\n" +
                    "我。。。",
            "5、“我把我房子卖掉啦！ ”\n" +
                    "\n" +
                    "“你是有多缺钱？房子也卖，打算租房吗？”\n" +
                    "“我还有九套！ ”\n" +
                    "“哦。。。”" };

    private String[] data2 = {
            "1、一直觉得自己笑点低，直到那天去超市买了个榴莲，没开，直接用绳子绑了一下拎走了，上电梯的时候我跟女友说了一句，谁敢惹我，我手里有个带味儿的流星锤!然后旁边一个妹子就哈哈哈哈哈哈的狂笑了一路。。。\n",
            "2、我有一个梦想，就是带着墨镜开着兰博基尼衣锦还乡，经过20多年的努力，已经实现一半了，我拥有了墨镜。\n",
            "3、说个斗奸商的真事!一次去买肉，“老板给我称一下这块多少钱”，“20块”，“太多了，切一半”，接着老板称了较小那块说“13块”。“给我另一半吧，给你7块钱”!那家伙半天没回过神!",
            "4、听说过吗？前世的五百次回眸，才换得今生的一次擦肩，象你我这样亲密的朋友，上辈子似乎没干什么，光他妈回头了！",
            "5、有两个造假钞的不小心造出面值15元的假钞，两人决定拿到偏远山区花掉，当他们拿一张15元买了1元的糖葫芦后，他们哭了，农民找了他们两张7块的" };

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
