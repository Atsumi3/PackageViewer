package jp.bizen.android.package_viewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import jp.bizen.android.lib.package_viewer.PackageViewerActivity;

public final class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, PackageViewerActivity.class));
    }
}
