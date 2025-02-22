var CONSTS = {
    MESSAGE: {
        edison_wfsimulation_new_confirm_message: 'Unsaved changes will be deleted. Do you want to continue?',
        edison_wfsimulation_export_success_message: 'Successfully exported',
        edison_wfsimulation_save_complete_message: 'Successfully saved',
        edison_wfsimulation_create_success_message: 'Successfully created',
        edison_wfsimulation_delete_success_message: 'Successfully deleted',
        edison_wfsimulation_submit_success_message: 'Successfully submitted',
        edison_wfsimulation_pause_success_message: 'Simulation Job successfully paused',
        edison_wfsimulation_resume_success_message: 'Simulation Job successfully resumed',
        edison_wfsimulation_submit_fail_message: 'Job submition failed',
        edison_wfsimulation_export_fail_message: 'Job export failed',
        edison_wfsimulation_pause_fail_message: 'Job pause failed',
        edison_wfsimulation_resume_fail_message: 'Job resume failed',
        edison_wfsimulation_select_first_message: 'Please select the simulation first',
        edison_wfsimulation_remove_confirm_message: 'Deleted data can not be recovered. Are you sure you want to delete?',
        edison_wfsimulation_no_selected_job_message: 'Please select the simulation job first',
        edison_wfsimulation_no_valid_node_data_message: 'Simulation Job Data is incorrect',
        edison_wfsimulation_validation_fail_message: 'Please fill all input values',
    },
    WF_STATUS_CODE: {
        WAITING: "WAITING",
        CREATED: "CREATED",
        RUNNING: "RUNNING",
        PAUSED: "PAUSED",
        COMPLETED: "SUCCESS",
        SUCCESS: "SUCCESS",
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
        LOG_PORTS: "logPorts",
        ENDPOINT: "endpoint",
        INPUT: "input",
        OUTPUT: "output",
        LOG: "log",
        PORT_ELEMENT: "port-element",
        LOOP: "loop",
    },
    WF_ENGINE: {
        CMD_PREFIX: "cmd",
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
    WF_NODE_CODE: {
    	IB_DATA: "ibData",
    	STATUS : "status",
    	IB_UUID : "ibUuid",
    	IB_SIM_UUID : "ibSimUuid",
    	WORKBENCH : "workbench",
    	IS_WORKBENCH: "isWorkbench",
    	WORKBENCH_DATA: "workbench_data"
    }
}
