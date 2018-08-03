# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Users\dell\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-target 1.6
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-dontwarn
-dump class_files.txt
-printseeds seeds.txt
-printusage unused.txt
-printmapping mapping.txt
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes *JavascriptInterface*
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
-dontwarn  com.google.**
-dontwarn org.apache.velocity.**
-dontwarn org.apache.commons.**
-dontwarn com.zhihu.matisse.**


-keep class java8.** { *; }
-keep interface java8.** { *; }
-dontwarn java8.**

-dontwarn  org.android.**
-keep public class org.json.** {*;}
-keep class com.google.gson.** { *;}
-keepattributes EnclosingMethod
-keepclasseswithmembernames class * {
    native <methods>;
}
#推送
-dontwarn com.ut.mini.**
-dontwarn com.google.common.**
-dontwarn  u.aly.bt
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**
-dontwarn java.lang.**



-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class org.apache.thrift.** {*;}

-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}

-keep public class **.R$*{
   public static final int *;
}
-keep class sun.misc.Unsafe { *; }
-keep class com.idea.fifaalarmclock.entitycls.***
-keep class com.google.gson.stream.** { *; }

-keepclassmembers class com.datanno.data.exchange.common.webview.CustomHostJsScope$RetJavaObj{ *; }
-keepclassmembers class com.datanno.data.exchange.common.webview.CustomHostJsScope{ *; }

-keep class com.datanno.data.exchange.net.response.**{*;}
-keep class com.datanno.data.exchange.net.request.**{*;}
-keep class com.datanno.data.exchange.entity.**{*;}
-keep class com.xiong.common.lib.net.response.**{*;}


-dontwarn java.nio.file.**
-dontwarn org.codehaus.**
-dontwarn com.tencent.connect.**
# share
-keep class com.tencent.mm.sdk.openapi.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.openapi.** implements com.tencent.mm.sdk.openapi.WXMed
-dontwarn com.tencent.mm.**
-keep class com.tencent.mm.**{*;}

# retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Exceptions
-keepattributes Signature
-keepattributes Exceptions
# Retrolambda
-dontwarn java.lang.invoke.*
-keep class java.lang.**{*;}

# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepattributes InnerClasses

# AndroidEventBus
-keep class org.simple.** { *;}
-keep interface org.simple.** { *;}
-keepclassmembers class * {
    @org.simple.eventbus.Subscriber <methods>;
}

#友盟统计
-dontwarn com.umeng.**
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**
-dontwarn com.meizu.**
-keepclassmembers class * {
     public <init>(org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class com.umeng.onlineconfig.**{*;}
-keep class com.umeng.onlineconfig.proguard.**{*;}
-keep class com.umeng.onlineconfig.OnlineConfigAgent.**{*;}

-keep class com.umeng.onlineconfig.OnlineConfigLog.**{*;}

-keep interface com.umeng.onlineconfig.UmengOnlineConfigureListener.**{*;}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}
-keep public class com.datanno.data.exchange.R$*{
public static final int *;
}
#eventbus
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

# ARouter
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider
-keep class * implements com.alibaba.android.arouter.facade.template.IProvider
-dontwarn com.alibaba.android.arouter.facade.model.*
#litesuits
-keep class com.datanno.data.exchange.libs.step.**{*;}
-keep class com.litesuits.orm.**{*;}
-dontwarn com.litesuits.android.**
#banner
-keep class com.youth.banner.** {
     *;
}

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule