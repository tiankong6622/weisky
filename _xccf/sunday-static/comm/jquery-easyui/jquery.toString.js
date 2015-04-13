jQuery.extend({      
   jsonToString:function(object){        
        var type = typeof object;        
        if ('object' == type) {          
            if (Array == object.constructor) 
                type = 'array';          
            else if (RegExp == object.constructor)   
                type = 'regexp';          
            else
                type = 'object';        
        }        
        switch (type) {        
            case 'undefined':        
            case 'unknown':          
                return;           
            case 'function':        
            case 'boolean':        
            case 'regexp':          
                return object.toString();          
            case 'number':          
                return isFinite(object) ?   object.toString() : 'null';          
            case 'string':          
                return '"' + object.replace(/(|")/g, "$1").replace(/n|r|t/g, function(){            
                            var a = arguments[0];           
                            return (a == 'n') ? 'n': (a == 'r') ? 'r': (a == 't') ? 't': ""         
                        }) + '"';          
            case 'object':          
                if (object === null) 
                    return 'null';          
                var results = [];  
                for (var property in object) {            
                    var value = jQuery.jsonToString(object[property]);            
                    if (value !== undefined) results.push(jQuery.jsonToString(property) + ':' + value);          
                }          
                return '{' + results.join(',') + '}';          
            case 'array':          
                var results = [];          
                for (var i = 0; i < object.length; i++) {            
                    var value = jQuery.jsonToString(object[i]);            
                    if (value !== undefined) results.push(value);         
                }          
                return '[' + results.join(',') + ']';         
        }      
    }    
});