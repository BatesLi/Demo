package cn.dankal.demo.Read;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.dankal.basic_lib.util.ToastUtil;
import cn.dankal.demo.R;
import cn.dankal.demo.Read.adapter.HomeFragmentPageAdapter;
import cn.dankal.demo.Read.bean.CheesesBean;
import cn.dankal.demo.Read.fragment.BlankFragment;
import cn.dankal.demo.Read.fragment.MusicFragment;
import cn.dankal.demo.Read.utils.MusicFileUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {

  @BindView(R.id.drawer_read) DrawerLayout mDrawerRead;
  @BindView(R.id.tool_bar_read) Toolbar mToolbarRead;
  @BindView(R.id.frame_layout_title_menu) FrameLayout mFrameLayoutTitleMenu;
  @BindView(R.id.img_title_menu) ImageView mImgTitleMenu;
  @BindView(R.id.radio_btn_group_control) RadioGroup mRadioBtnGroupContorl;
  @BindView(R.id.radio_btn_daily) RadioButton mRadioBtnDaily;
  @BindView(R.id.radio_btn_music) RadioButton mRadioBtnMusic;
  @BindView(R.id.radio_btn_friend) RadioButton mRadioBtnFriend;
  @BindView(R.id.view_pager_content) ViewPager mViewPagerContent;
  @BindView(R.id.list_view_music) ListView mListViewMusic;//添加listView之后，导致ViewPager失效 # 已解决

  private android.support.v7.widget.SearchView mViewSearch;
  //private SearchView.SearchAutoComplete mSearchAutoComplete;
  private static final int REQ_PERMISSION = 100;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_read);
    ButterKnife.bind(this);

    setToolbarRead();
    initView();
    requestPermission();
  }

  public void setToolbarRead() {
    //去除toolbar的默认title
    setSupportActionBar(mToolbarRead);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    mToolbarRead.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        /*if (mSearchAutoComplete.isShown()) {
            try {
              mSearchAutoComplete.setText("");
              Method method = mViewSearch.getClass().getDeclaredMethod("onCloseClicked");
              method.setAccessible(true);
              method.invoke(mViewSearch);
            }catch (Exception e) {
              e.printStackTrace();
            }
        }else {
          finish();
        }*/
      }
    });
  }

  private void initView() {
    mRadioBtnGroupContorl.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
          case R.id.radio_btn_daily:
            mViewPagerContent.setCurrentItem(0);//设置当前页面
            break;
          case R.id.radio_btn_music:
            mViewPagerContent.setCurrentItem(1);
            break;
          case R.id.radio_btn_friend:
            mViewPagerContent.setCurrentItem(2);
            break;
        }
      }
    });
    List<Fragment> fragmentList = new ArrayList<>();
    fragmentList.add(new BlankFragment());
    fragmentList.add(new MusicFragment());
    fragmentList.add(new BlankFragment());

    mViewPagerContent.setAdapter(
        new HomeFragmentPageAdapter(getSupportFragmentManager(), fragmentList));
    mViewPagerContent.setCurrentItem(1);
    mViewPagerContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      //onPageSelected(int position)：这个方法有一个参数position，代表哪个页面被选中。
      @Override public void onPageSelected(int position) {
        switch (position) {
          case 0:
            mRadioBtnGroupContorl.check(R.id.radio_btn_daily);
            break;
          case 1:
            mRadioBtnGroupContorl.check(R.id.radio_btn_music);
            break;
          case 2:
            mRadioBtnGroupContorl.check(R.id.radio_btn_friend);
            break;
        }
      }
      @Override public void onPageScrollStateChanged(int state) {
      }
    });
  }

  //搜索模块menu
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_search, menu);
    MenuItem searchItem = menu.findItem(R.id.action_search);
    mViewSearch = (SearchView) MenuItemCompat.getActionView(searchItem);//无法强转(SearchView导入错误)# 解决
    //mSearchAutoComplete = (SearchView.SearchAutoComplete) mViewSearch.findViewById(R.id.search_src_text);
    mViewSearch.setQueryHint("输入歌曲名查找");
    mViewSearch.setSubmitButtonEnabled(true);//搜索框展开时显示提交按钮

    /*//设置输入框提示文字样式
    mSearchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.darker_gray));
    mSearchAutoComplete.setTextColor(getResources().getColor(android.R.color.background_light));
    mSearchAutoComplete.setTextSize(14);
    //设置触发查询的最少字符数（默认2个字符才会触发查询）
    mSearchAutoComplete.setThreshold(1);*/

    //搜索框文字变化监听
    mViewSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String query) {
        ToastUtil.toToast("搜索的内容：" + query);
        return true;
      }

      @Override public boolean onQueryTextChange(String newText) {
        FuzzyLookupMusic(newText);
       /* Cursor cursor = TextUtils.isEmpty(newText) ? null : queryData(newText);
        if (mViewSearch.getSuggestionsAdapter() == null) {
                            mViewSearch.setSuggestionsAdapter(new SimpleCursorAdapter(ReadActivity.this
                            , R.layout.item_search_show_music, cursor, new String[]{"name"}, new int[]{R.id.text1}));
                       } else {
                            mViewSearch.getSuggestionsAdapter().changeCursor(cursor);
                       }*/
        return false;
      }
    });
    return super.onCreateOptionsMenu(menu);
  }

  //搜索模块menu同时显示文字与图片
  @Override
  public boolean onMenuOpened(int featureId, Menu menu) {
    if (menu != null) {
      if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
        try {
          Method method =
              menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
          method.setAccessible(true);
          method.invoke(menu, true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return true;
  }

  //获取SD卡权限(检查并申请权限) #系统无提示??? 权限申请需要在manifests文件申请
  private void requestPermission() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission
        .READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      //如果没有读取到SD卡权限就
      ActivityCompat.requestPermissions(this,
          new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}
          , REQ_PERMISSION);
    }
  }

  /*用户选择允许或拒绝后，会回调onRequestPermissionsResult方法
  , 该方法类似于onActivityResult(在fragment中请求的方法)*/
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions
      , @NonNull int[] grantResults) {
    if (requestCode == REQ_PERMISSION) {
      if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
        ToastUtil.toToast("读取SD卡权限被拒绝了");
      } else {
        ToastUtil.toToast("同意读取SD卡权限");
      }
    }
  }

  /*
   * 搜索结果的list
   * 模糊查找本地音乐模块(采用listView展示的方式)
   * */
  private void FuzzyLookupMusic(String key) {
    String[] music = new String[] {};//假设展示搜索的结果是String类型的字符串数组
    //进行搜索结果判定
    if (!TextUtils.isEmpty(key)) {
      music = MusicFileUtils.queryMusic(this, key);
    }
    ArrayAdapter<String> musicAdapter = new ArrayAdapter<>(
        this, android.R.layout.simple_list_item_1, music);
    mListViewMusic.setAdapter(musicAdapter);
  }

  /*
   * 查找本地音乐模块(采用 弹出式搜索提示 展示的方式的数据库表来自 SQLiteDatabase)
   * */
  private Cursor queryData(String key) {
    SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + "music.db", null);
    Cursor cursor = null;
    try {
      String querySql = "select * from tb_music where name like'% + " + key + "%'";
      cursor = database.rawQuery(querySql, null);
    } catch (Exception e) {
      e.printStackTrace();
      //创建表SQL语句,id（主键且自动增加）
      String createSql =
          "create table tb_music (_id integer primary key autoincrement,name varchar(100))";
      database.execSQL(createSql);

      String insertSql = "insert into tb_music values(null,?)";
      for (int i = 0; i < CheesesBean.sCheeseStrings.length; i++) {
        database.execSQL(insertSql, new String[] {CheesesBean.sCheeseStrings[i]});
      }
      String querySql = "select * from tb_music where name like'%" + key + "%'";
      cursor = database.rawQuery(querySql, null);

      Log.e("CSDN_LQR", "createSql = " + createSql);
      Log.e("CSDN_LQR", "querySql = " + querySql);
    }
    return cursor;
  }
}





