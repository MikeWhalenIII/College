function calcMean(array) {
    const sum = calcSum(array);
    const mean = sum / array.length;
    return mean.toFixed(2);
}

function calcMedian(array) {
    const middle = Math.floor(array.length / 2);

    if (array.length % 2) {
        return array[middle].toFixed(2);
    } else {
        return ((array[middle - 1] + array[middle]) / 2.0).toFixed(2);
    }
}

function calcMode(array) {
    let max = 0, mode = [], count = [], num;

    for (let index = 0; index < array.length; index++) {
        num = array[index];

        count[num] = (count[num] || 0) + 1;
        if (count[num] > max) {
            max = count[num];
        }
    }
    for (x in count) {
        if (count.hasOwnProperty(x)) {
            if (count[x] == max) {
                mode.push(x);
            }
        }
    }
    return mode.join(' ');
}

function calcStdDev(array) {
    const variance = calcVariance(array);
    return Math.sqrt(variance).toFixed(2);
}

function calcSum(array) {
    let sum = 0;

    array.forEach(element => {
        sum += element;
    });
    return sum.toFixed(2);
}

function calcVariance(array) {
    const mean = calcMean(array);
    let difference = 0;

    for (let index = 0; index < array.length; index++) {
        difference += Math.pow((array[index] - mean), 2);
    }
    return (difference / array.length).toFixed(2);
}

function findMax(array) {
    return Math.max.apply(null, array).toFixed(2);
}

function findMin(array) {
    return Math.min.apply(null, array).toFixed(2);
}

function performStatistics() {

    // Assign values entered in the text area and validate.
    const numericArray = document.getElementById("userInput").value.split(' ');
    numericArray.sort(function (a, b) { return a - b }); // Sort array

    if (numericArray.length < 5 || numericArray.length > 20) {
        alert("Please enter 5 to 20 numbers.");
        return false;
    }
    for (i = 0; i < numericArray.length; i++) {
        // Check to see if all elements are numbers
        if (isNaN(numericArray[i]) == true) {
            alert("Please only provide numbers.");
            return false;
        } else {
            // Convert array of strings to numeric values
            numericArray[i] = parseInt(numericArray[i]);
        }
        // Check to make sume numbers are between 0 and 100
        if (numericArray[i] > 100 || numericArray[i] < 0) {
            alert("The numbers must range from 0 to 100.");
            return false;
        }
    }

    document.getElementById("sum").value = calcSum(numericArray);
    document.getElementById("mean").value = calcMean(numericArray);
    document.getElementById("median").value = calcMedian(numericArray);
    document.getElementById("variance").value = calcVariance(numericArray);
    document.getElementById("stdDev").value = calcStdDev(numericArray);
    document.getElementById("max").value = findMax(numericArray);
    document.getElementById("min").value = findMin(numericArray);
    document.getElementById("mode").value = calcMode(numericArray);
    
    return false;
}