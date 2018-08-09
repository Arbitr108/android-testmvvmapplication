package android.smartdeveloper.ru.data.net;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.QueueDispatcher;
import okhttp3.mockwebserver.RecordedRequest;

class MockServerDispatcher  extends QueueDispatcher {
        @Override
        public MockResponse dispatch(RecordedRequest request) {

            if(request.getPath().equals("api/data")){
                return new MockResponse().setResponseCode(200).setBody("{data:FakeData}");
            }else if(request.getPath().equals("api/codes")){
                return new MockResponse().setResponseCode(200).setBody("{codes:FakeCode}");
            }else if(request.getPath().equals("api/number"))
                return new MockResponse().setResponseCode(200).setBody("number:FakeNumber");

            return new MockResponse().setResponseCode(404);
        }
}