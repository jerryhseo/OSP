var WF_STATUS_CODE = {
    CREATED: "CREATED",
    RUNNING: "RUNNING",
    PAUSED: "PAUSED",
    COMPLETED: "SUCCESS",
    DONE: "DONE",
    FAILED: "FAILED",
    CANCELED: "CANCELED",
    NOT_FOUND: "NOT_FOUND"
};
var WF_STATUS_CODE_STRING = {
    CREATED: "Created",
    RUNNING: "Running",
    PAUSED: "Paused",
    COMPLETED: "Completed",
    SUCCESS: "Success",
    DONE: "Done",
    FAILED: "Failed",
    CANCELED: "Canceled"
};

var WF_JSPLUMB_TYPES = {
    ENDPOINT: "endpoint",
    INPUT: "input",
    OUTPUT: "output",
    LOOP: "loop"
};

var WF_CONVERTER_SCRIPT = "converter-script";

var WF_APP_TYPES = {
    DYNAMIC_CONVERTER: {
        NAME: "DynamicConverter",
        INPUT_DATA_TYPE: "converter_input",
        OUTPUT_DATA_TYPE: "converter_stdout",
        INPUT_SCOPE: "converter_stdout_",
        OUTPUT_SCOPE: "converter_input_",
    },CONTROLLER: {
        NAME: "Controller",
        INPUT_DATA_TYPE: "controller_input",
        OUTPUT_DATA_TYPE: "controller_stdout",
        INPUT_SCOPE: "controller_stdout_",
        OUTPUT_SCOPE: "controller_input_",
    },
    STATIC_CONVERTER: {
        NAME: "Converter"
    },
    APP: {
        NAME: "Solver"
    }
};