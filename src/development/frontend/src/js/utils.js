export function removeElementFromArray(arr, value){
    for( var i = 0; i < arr.length; i++){ 
        if ( arr[i] === value) { 
            arr.specs.splice(i, 1); 
        }
    }
}