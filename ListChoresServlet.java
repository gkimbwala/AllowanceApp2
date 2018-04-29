package myapp;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListChoresServlet extends HttpServlet {

    @Inject
    AllowanceService allowanceService;
    @Inject
    Moshi moshi;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (BufferedSource source = Okio.buffer(Okio.source(req.getInputStream()));
             BufferedSink sink = Okio.buffer(Okio.sink(resp.getOutputStream()))) {

            JsonAdapter<ListChoresServlet.ListChoreRequest> requestAdapter = moshi.adapter(ListChoresServlet.ListChoreRequest.class);
            ListChoresServlet.ListChoreRequest request = requestAdapter.fromJson(source);

            List<Chore> listChores = allowanceService.listChores(new UserId(request.userId));

            ListChoresServlet.ListChoreResponse response = new ListChoresServlet.ListChoreResponse();
            response.chores = listChores;

            JsonAdapter<ListChoresServlet.ListChoreResponse> responseAdapter = moshi.adapter(ListChoresServlet.ListChoreResponse.class);
            responseAdapter.toJson(sink, response);
        }
    }

    static class ListChoreRequest {
        String userId;
    }

    static class ListChoreResponse {
        String userId;
        List<Chore> chores;
    }
}

