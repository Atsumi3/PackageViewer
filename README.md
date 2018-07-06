# PackageViewer  
![Release](https://jitpack.io/v/Atsumi3/PackageViewer.svg)  
Android開発でのデバッグのためにパッケージ一覧表示したかった  

## 使用例

どこでもいいので NKClassListActivity を呼ぶ
```java
// 例えばActivityでなら
startActivity(new Intent(this, NKClassListActivity.class));
```

## 注意  
リリースビルドには含まないほうがいいです

## 使用方法
jitpackを使っています  
Root の build.gradle に以下を追加して、
``` java
allprojects {
  repositories {
  ...
   maven { url 'https://jitpack.io' }
  }
}
```

Projectのbuild.gradle に以下を追加してください
``` java
dependencies {
  ...
  compile 'com.github.Atsumi3:PackageViewer:$latestVersion'
}
```
