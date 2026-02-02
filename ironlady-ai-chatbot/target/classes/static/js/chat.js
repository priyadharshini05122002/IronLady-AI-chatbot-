document.addEventListener("DOMContentLoaded", () => {
    const elements = {
        chatBox:    document.getElementById("chatBox"),
        input:      document.getElementById("userInput"),
        sendBtn:    document.getElementById("sendBtn"),
    };

    if (!elements.chatBox || !elements.input || !elements.sendBtn) {
        console.error("Critical DOM elements missing");
        return;
    }

    let isSending = false;

    // Auto-resize textarea
    elements.input.addEventListener("input", () => {
        elements.input.style.height = "auto";
        elements.input.style.height = Math.min(elements.input.scrollHeight, 140) + "px";
    });

    // Send on Enter (Shift+Enter = newline)
    elements.input.addEventListener("keydown", (e) => {
        if (e.key === "Enter" && !e.shiftKey) {
            e.preventDefault();
            sendMessage();
        }
    });

    elements.sendBtn.addEventListener("click", sendMessage);

    function sendMessage() {
        if (isSending) return;

        const text = elements.input.value.trim();
        if (!text) return;

        isSending = true;
        elements.sendBtn.disabled = true;

        // Show user message
        appendMessage("user", text);

        // Clear & reset input
        elements.input.value = "";
        elements.input.style.height = "46px";
        elements.input.focus();

        // Show typing
        const typing = showTyping();

        fetch("/chat", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: "message=" + encodeURIComponent(text)
        })
        .then(r => {
            if (!r.ok) throw new Error(`HTTP ${r.status}`);
            return r.text();
        })
        .then(reply => {
            hideTyping(typing);
            appendMessage("ai", reply);
        })
        .catch(err => {
            console.error(err);
            hideTyping(typing);
            appendMessage("system", "⚠️ Sorry, something went wrong. Please try again.");
        })
        .finally(() => {
            isSending = false;
            elements.sendBtn.disabled = false;
        });
    }

    function appendMessage(type, content) {
        const msg = document.createElement("div");
        msg.className = `message ${type}`;

        const avatar = document.createElement("div");
        avatar.className = "avatar";
        avatar.textContent = type === "user" ? "👩" : type === "ai" ? "💃" : "⚙️";

        const bubble = document.createElement("div");
        bubble.className = "bubble";
        bubble.innerHTML = content.replace(/\n/g, "<br>");  // simple line breaks

        msg.append(avatar, bubble);
        elements.chatBox.appendChild(msg);

        // Scroll to bottom
        elements.chatBox.scrollTop = elements.chatBox.scrollHeight;
    }

    function showTyping() {
        const div = document.createElement("div");
        div.className = "typing-indicator message ai";
        div.innerHTML = `
            <div class="avatar">💃</div>
            <div class="bubble">
                <div class="typing-dots">
                    <span></span><span></span><span></span>
                </div>
            </div>
        `;
        elements.chatBox.appendChild(div);
        elements.chatBox.scrollTop = elements.chatBox.scrollHeight;
        return div;
    }
	
	fetch("/chat", {
	    method: "POST",
	    headers: {
	        "Content-Type": "application/x-www-form-urlencoded"
	    },
	    body: "message=__IMAGE__"
	});

    function hideTyping(el) {
        if (el && el.parentNode) el.remove();
    }

    // Initial focus
    elements.input.focus();
});