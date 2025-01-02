/**
 * this function gets nothing and returns a string contains current time and date concat
 * @returns "string with current time and date concat"
 */
function currentTimeAndDateString() {
  const getCurrentTime = function (minutes) {
    if (minutes < 10) {
      return "0" + minutes.toString();
    } else {
      return minutes;
    }
  };
  var tempDate = new Date();
  var dateTimeStr =
    "" +
    getCurrentTime(tempDate.getHours()) +
    ":" +
    getCurrentTime(tempDate.getMinutes()) +
    " " +
    tempDate.getDate() +
    "/" +
    (tempDate.getMonth() + 1) +
    "/" +
    tempDate.getFullYear().toString().substring(2);
  return dateTimeStr;
}

export default currentTimeAndDateString;
