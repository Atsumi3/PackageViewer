# PackageViewer  
![Release](https://jitpack.io/v/Atsumi3/PackageViewer.svg)  
Android開発でのデバッグのためにアプリ内部のディレクトリ構成を表示したかった

## 使用例

どこでもいいので PackageViewerActivity を呼ぶ
```java
// 例えばActivityでなら
startActivity(new Intent(this, PackageViewerActivity.class));
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
  implementation 'com.github.Atsumi3:PackageViewer:$latestVersion'
}
```
