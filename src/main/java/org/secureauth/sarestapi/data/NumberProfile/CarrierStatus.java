
package org.secureauth.sarestapi.data.NumberProfile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.secureauth.sarestapi.util.JSONUtil;

/**
 * Created by rrowcliffe on 12/27/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "reason"
})
public class CarrierStatus {

    @JsonProperty("status")
    private String status;
    @JsonProperty("reason")
    private Object reason;

    /**
     * No args constructor for use in serialization
     *
     */
    public CarrierStatus() {
    }

    /**
     *
     * @param reason explanation for the state of the carrier (network_type, ported, country, carrier) based on the configuration defined in the Web Admin
     * @param status state of the carrier (blocked, allowed) based on the configuration defined in the Web Admin
     */
    public CarrierStatus(String status, Object reason) {
        super();
        this.status = status;
        this.reason = reason;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public CarrierStatus withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("reason")
    public Object getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(Object reason) {
        this.reason = reason;
    }

    public CarrierStatus withReason(Object reason) {
        this.reason = reason;
        return this;
    }

    @Override
    public String toString(){
        return JSONUtil.convertObjectToJSON(this);
    }
}
