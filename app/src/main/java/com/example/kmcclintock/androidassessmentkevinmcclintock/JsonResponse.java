package com.example.kmcclintock.androidassessmentkevinmcclintock;

import android.widget.Adapter;

import java.util.List;

/**
 * Created by KMcClintock on 8/06/2016.
 * Used between MainActivity and JsonTask
 */
public interface JsonResponse {

    void processFinish(Adapter adapter);

}
