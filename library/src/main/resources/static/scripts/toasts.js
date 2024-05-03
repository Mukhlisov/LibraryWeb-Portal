let icon = { 
	success: 
	'<span class="material-symbols-outlined">task_alt</span>', 
	danger: 
	'<span class="material-symbols-outlined">error</span>', 
}; 

const showToast = ( 
	message = "Sample Message", 
	toastType = "info", 
	duration = 5000) => { 
	if ( 
		!Object.keys(icon).includes(toastType)) 
		toastType = "info"; 

	let box = document.createElement("div"); 
	box.classList.add( 
		"toast", `toast-${toastType}`); 
	box.innerHTML = ` <div class="toast-content-wrapper"> 			 
					<div class="toast-message">${message}</div> 
					<div class="toast-progress"></div> 
					</div>`; 
	duration = duration || 5000; 
	box.querySelector(".toast-progress").style.animationDuration = 
			`${duration / 1000}s`; 

	let toastAlready = 
		document.body.querySelector(".toast"); 
	if (toastAlready) { 
		toastAlready.remove(); 
	} 

	document.body.appendChild(box)}; 

document.addEventListener("DOMContentLoaded", function() {
    let messageElement = document.querySelector("#message");
    let message = messageElement.dataset.message;
    if (message && message !== "") {
        if (message.includes(':')){
            showToast(message, "danger", 4000);
        } else {
            showToast(message, "success", 4000);
        }
    }
});