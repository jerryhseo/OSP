var CONSTS = {
    MESSAGE: {
        edison_wfsimulation_new_confirm_message: 'Unsaved changes will be deleted. Do you want to continue?',
        edison_wfsimulation_create_success_message: 'Successfully created.',
        edison_wfsimulation_select_first_message: 'Please select the simulation first',
        ediso_wfsimulation_remove_confirm_message: 'Deleted simulation can not be recovered. Are you sure you want to delete?'
    },
    WF_STATUS_CODE: {
        CREATED: "CREATED",
        RUNNING: "RUNNING",
        PAUSED: "PAUSED",
        COMPLETED: "SUCCESS",
        DONE: "DONE",
        FAILED: "FAILED",
        CANCELED: "CANCELED",
        NOT_FOUND: "NOT_FOUND"
    },
    WF_STATUS_CODE_STRING: {
        CREATED: "Created",
        RUNNING: "Running",
        PAUSED: "Paused",
        COMPLETED: "Completed",
        SUCCESS: "Success",
        DONE: "Done",
        FAILED: "Failed",
        CANCELED: "Canceled"
    },
    WF_JSPLUMB_TYPES: {
        OTHER_PORTS: "all",
        INPUT_PORTS: "inputPorts",
        OUTPUT_PORTS: "outputPorts",
        ENDPOINT: "endpoint",
        INPUT: "input",
        OUTPUT: "output",
        LOOP: "loop",
    },
    WF_CONVERTER_SCRIPT: "converter-script",
    WF_CONDITION_SCRIPT: "condition-script",
    WF_APP_TYPES: {
        DYNAMIC_CONVERTER: {
            NAME: "DynamicConverter",
            INPUT_DATA_TYPE: "converter_input",
            OUTPUT_DATA_TYPE: "converter_stdout",
            INPUT_SCOPE: "converter_stdout_",
            OUTPUT_SCOPE: "converter_input_",
        }, CONTROLLER: {
            NAME: "Controller",
            INPUT_DATA_TYPE: "controller_input",
            OUTPUT_DATA_TYPE: "controller_stdout",
            INPUT_SCOPE: "controller_stdout_",
            OUTPUT_SCOPE: "controller_input_",
        }, FILE_COMPONENT: {
            NAME: "FileComponent",
            INPUT_DATA_TYPE: "componet_input",
            OUTPUT_DATA_TYPE: "componet_stdout",
            INPUT_SCOPE: "componet_stdout_",
            OUTPUT_SCOPE: "componet_input_",
        },
        STATIC_CONVERTER: {
            NAME: "Converter"
        },
        APP: {
            NAME: "Solver"
        },
        GROUP: {
            NAME: "GroupComponent"
        }
    },
}
