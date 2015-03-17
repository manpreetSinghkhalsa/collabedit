require(['dojo/on'],
    function(on) {
        cm.on('change', function(arg1, arg2) {
            actionToPerform = arg2.origin;
            lineLocation = arg2.from.line;
            charLocation = arg2.from.ch;
            data = arg2.text;
            console.log("Action: ", actionToPerform, " Data: ", data, " line: ", lineLocation, " charLocation: ", charLocation)
        });
    }
    
);
