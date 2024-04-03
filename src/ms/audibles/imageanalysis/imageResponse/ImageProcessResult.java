package ms.audibles.imageanalysis.imageResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageProcessResult {
    CaptionResult cResult;
    ReadResult rResult;

    @JsonProperty("captionResult")
    public CaptionResult getcResult() {
        return this.cResult; }
    public void setcResult(CaptionResult text) {
        this.cResult = text; }

    @JsonProperty("readResult")
    public ReadResult getrResult() {
        return this.rResult; }
    public void setrResult(ReadResult text) {
        this.rResult = text; }

}
