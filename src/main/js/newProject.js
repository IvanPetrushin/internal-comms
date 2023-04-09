let currentDate = new Date;
currentDate.setTime(Date.now());

document.getElementById('expires').min = currentDate.toISOString().slice(0, 10);
console.log(currentDate.toISOString().slice(0, 10));

