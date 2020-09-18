package com.example.artrial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.ar.*;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.BaseTransformableNode;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {
    ArFragment frag;
    ModelRenderable pointer;
    int nodeset;
    HitResult firstnode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nodeset=0;
        frag=(ArFragment)getSupportFragmentManager().findFragmentById(R.id.frag);
        ClientThread.con=this;
        frag.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener(){
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
               Log.d("Hit","hit");
               if(nodeset==2)
                   return;
                firstnode=hitResult;
                Anchor anchor=hitResult.createAnchor();
                AnchorNode anchorNode=new AnchorNode(anchor);

                anchorNode.setParent(frag.getArSceneView().getScene());
                TransformableNode transformableNode=new TransformableNode(frag.getTransformationSystem());
                transformableNode.setParent(anchorNode);
                transformableNode.setRenderable(pointer);
                transformableNode.select();
                transformableNode.setOnTapListener(new BaseTransformableNode.OnTapListener() {

                    @Override
                    public void onTap(HitTestResult hitTestResult, MotionEvent motionEvent){
                        ClientThread.Message = "5";
                        ClientThread.con = MainActivity.this;
                        Log.d("Same hit", "Same hit");
                        new Thread(new ClientThread()).start();
                    }
                });
                Log.d("end hit","hit hit");

                ModelRenderable.builder()
                        .setSource(getBaseContext(),R.raw.marker)
                        .build()
                        .thenAccept(renderable->pointer=renderable);

                nodeset++;
            }
        });

    }
}
