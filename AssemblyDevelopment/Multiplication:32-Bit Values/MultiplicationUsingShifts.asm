; Question01 (question1.asm)
;Author: Joshua Renelli 

INCLUDE /irvine/irvine32.inc
includelib	/irvine/irvine32.lib
includelib	/irvine/user32.lib
includelib	/irvine/kernel32.lib

.data
prompt byte "Please enter a signed integer value (max 32767, least -32768)",0
errorMsg byte "That number is too large!",0
bxSign	byte	0
cxSign	byte 0
sum	sdword	0

.code
main PROC
;Retrieve and store the first integer
	call		PromptAndGetInt
	mov		bx,ax
	jns		NextInt		;Dealing with a signed integer
	neg		bx
	mov		bxSign,1		;Boolean value set if signed

NextInt:
;Retrieve and store the second integer
	call		PromptAndGetInt
	mov		cx,ax
	jns		Next			;Dealing with a signed integer
	neg		cx
	mov		cxSign,1		;Boolean value set if signed
Next:
	call		Multiply		;Multiplying and displaying result
	exit
main ENDP

;This procedure multiplies two 16bit integers using shifts and additions
; Takes in the integers in the bx and cx registers
; The result will be displayed to the screen
Multiply PROC
mov		edx,14		;Setting loop counter for the 15 bits you must check
;Loop through the number in cx to find out when to shift
LoopNum:
;Making sure ax always contains the number you are multiplying by
	mov		ax,bx
;Bit testing for a 1 to see if you have to shift and how many times to shift by
	bt		cx,dx
	jc		ShiftAdd
	dec		edx
	cmp		dl,0FFh
	jne		LoopNum
	jmp		Done
;Deals with the final case if the lsb is a 1
ShiftAdd:
	push		dx
	cmp		dx,0
	je		Adding
;Deals with shifting the the number the correct amount of times
Shift:
	clc
	shl		ax,1
	jc		error		;Error will set if you shift too many times(ie. the number is too large)
	dec		dx
	cmp		dx,0
	jne		Shift
;Add the result of the shift to the sum variable
Adding:
	add		WORD PTR sum,ax
;Checking for an overflow after addition
	cmp		sum,32767
	jg		error
	cmp		sum,-32768
	jl		error
	pop		dx
;Dealing with the loop
	dec		edx
	cmp		dl,0FFh
	jne		LoopNum
	jmp		Done
;Handling an error involving an overflow
Error:
	mov		edx, OFFSET errorMsg
	CALL		WriteString
	ret
;Outputing the final value to screen
Done:
	xor		eax,eax
	mov		ax, WORD PTR sum
	mov		cl,cxSign
	xor		bxSign,cl			;Checking to see if you have to deal with a final result that is signed
	jnz		Signed
	call		WriteInt
	call		Crlf
	ret
;Negating final result if it must be negative
Signed:
	neg		eax
	call		WriteInt
	call		Crlf
	ret
Multiply ENDP

;This procedure gets an integer value from the user
; directly access's prompt
; the integer will be stored in eax
PromptAndGetInt PROC
	xor		eax,eax

	;<prompt to user> "Please enter an integer value: "
	mov		edx, OFFSET prompt
	call		WriteString

	;<getinteger>
	call		ReadInt

	;<return user input integer>
	ret
PromptAndGetInt ENDP

END main
