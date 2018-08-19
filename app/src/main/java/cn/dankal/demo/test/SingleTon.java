package cn.dankal.demo.test;

import android.content.Context;

public class SingleTon {

   private static SingleTon singleton = null;
   private Context mContext;

   public SingleTon(Context mContext) {
      //使用全局的context避免单例模式导致内存泄漏
      this.mContext = mContext.getApplicationContext();
   }

   public static SingleTon getSingleton(Context context){
      if (null == singleton){
         singleton = new SingleTon(context);
      }
      return singleton;
   }
}
