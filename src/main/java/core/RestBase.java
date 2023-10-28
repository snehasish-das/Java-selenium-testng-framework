package core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.Map;

public class RestBase extends Base{
    public static Map<String,String> ENVIRONMENT;
    public RestBase() throws IOException {
        ENVIRONMENT = jsonFileToMap("config/env.conf.json");
    }

    /**
     * set the base uri and content type as JSON
     * @return
     */
    public RequestSpecification setup(){
        return RestAssured
                .given()
                .baseUri(ENVIRONMENT.get("restBaseUrl"))
                .contentType(ContentType.JSON);

    }

    /**
     * to perform a get request with query params and additional headers
     * note: authorization, if any, needs to be embedded as a part of the headers
     * @param endpoint
     * @param queryParams
     * @param headers
     * @return
     */
    public Response get(String endpoint, Map<String,String> queryParams, Map<String,String> ...headers){
        RequestSpecification reqSpec = new RequestSpecBuilder().build();
        if(!queryParams.isEmpty()){
            reqSpec.queryParams(queryParams);
        }
        if(headers.length>0){
            reqSpec.headers(headers[0]);
        }

        Response response = setup().spec(reqSpec).when().log().all().get(endpoint);
        info("Response: \n"+response);
        return response;
    }

    /**
     * to perform a post request with payload and additional headers
     * note: authorization, if any, needs to be embedded as a part of the headers
     * @param endpoint
     * @param payload
     * @param headers
     * @return
     */
    public Response post(String endpoint, String payload, Map<String,String> ...headers){
        RequestSpecification reqSpec = new RequestSpecBuilder().build().body(payload);

        if(headers.length>0){
            reqSpec.headers(headers[0]);
        }

        Response response = setup().spec(reqSpec).when().log().all().post(endpoint);
        info("Response: \n"+response);
        return response;
    }

    /**
     * to perform a put request with payload and additional headers
     * note: authorization, if any, needs to be embedded as a part of the headers
     * @param endpoint
     * @param payload
     * @param headers
     * @return
     */
    public Response put(String endpoint, String payload, Map<String,String> ...headers){
        RequestSpecification reqSpec = new RequestSpecBuilder().build().body(payload);

        if(headers.length>0){
            reqSpec.headers(headers[0]);
        }

        Response response = setup().spec(reqSpec).when().log().all().put(endpoint);
        info("Response: \n"+response);
        return response;
    }

    /**
     * to perform a patch request with payload and additional headers
     * note: authorization, if any, needs to be embedded as a part of the headers
     * @param endpoint
     * @param payload
     * @param headers
     * @return
     */
    public Response patch(String endpoint, String payload, Map<String,String> ...headers){
        RequestSpecification reqSpec = new RequestSpecBuilder().build().body(payload);

        if(headers.length>0){
            reqSpec.headers(headers[0]);
        }

        Response response = setup().spec(reqSpec).when().log().all().patch(endpoint);
        info("Response: \n"+response);
        return response;
    }

    /**
     * to perform a delete request with query params and additional headers
     * note: authorization, if any, needs to be embedded as a part of the headers
     * @param endpoint
     * @param headers
     * @return
     */
    public Response delete(String endpoint, Map<String,String> ...headers){
        RequestSpecification reqSpec = new RequestSpecBuilder().build();
        if(headers.length>0){
            reqSpec.headers(headers[0]);
        }

        Response response = setup().spec(reqSpec).when().log().all().delete(endpoint);
        info("Response: \n"+response);
        return response;
    }
}
