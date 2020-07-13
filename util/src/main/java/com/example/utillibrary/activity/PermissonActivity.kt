package com.example.utillibrary.activity

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.utillibrary.PermissionUtil
import com.example.utillibrary.R
import kotlinx.android.synthetic.main.activity_permisson.*

class PermissonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permisson)
        btn_request_single.setOnClickListener {
            PermissionUtil.checkPermission(this, arrayOf(android.Manifest.permission.CAMERA), permissonCallback = object : PermissionUtil.OnPermissonCallback {
                override fun isGrant(grant: Boolean) {
                    if (grant) {
                        Toast.makeText(this@PermissonActivity, "success", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@PermissonActivity, "failed", Toast.LENGTH_SHORT).show()

                    }
                }
            })
        }
        btn_request_more.setOnClickListener {
            PermissionUtil.checkPermission(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE), permissonCallback = object : PermissionUtil.OnPermissonCallback {
                override fun isGrant(grant: Boolean) {
                    if (grant) {
                        Toast.makeText(this@PermissonActivity, "success", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@PermissonActivity, "failed", Toast.LENGTH_SHORT).show()

                    }
                }
            })
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        PermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
