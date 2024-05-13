package com.bignerdranch.android.sportsgbt;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

// sk-proj-ZBNCuTDy8zskyEJml8rPT3BlbkFJnj4SmhOZzyFLTTXuIDOG
public interface OpenAIInterface {
    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer sk-proj-6u7QhAusOyhadWsjYz9OT3BlbkFJr7OgtXtUQ0O04olYVdda"
    })
    @POST("https://api.openai.com/v1/chat/completions")
    Call<ResponseBody> getCompletion(@Body AIRequest aiRequest);
}
