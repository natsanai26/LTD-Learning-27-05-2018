package com.example.windows10.ltd_learning;

/**
 * Created by Windows10 on 4/17/2018.
 */

public class UpdateProgress {

    /**
     * response : {"status":true,"message":"Update successfully"}
     * progress : {"idprogress":65,"percent":50,"sectionId":179}
     */

    private ResponseBean response;
    private ProgressBean progress;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public ProgressBean getProgress() {
        return progress;
    }

    public void setProgress(ProgressBean progress) {
        this.progress = progress;
    }

    public static class ResponseBean {
        /**
         * status : true
         * message : Update successfully
         */

        private boolean status;
        private String message;

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class ProgressBean {
        /**
         * idprogress : 65
         * percent : 50
         * sectionId : 179
         */

        private int idprogress;
        private int percent;
        private int sectionId;

        public int getIdprogress() {
            return idprogress;
        }

        public void setIdprogress(int idprogress) {
            this.idprogress = idprogress;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public int getSectionId() {
            return sectionId;
        }

        public void setSectionId(int sectionId) {
            this.sectionId = sectionId;
        }
    }
}
