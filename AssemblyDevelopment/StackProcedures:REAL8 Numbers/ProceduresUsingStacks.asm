;Question1      (question1.asm)
;This question was completed by Joshua Renelli and Paolo Scola

INCLUDE /irvine/irvine32.inc
includelib	/irvine/irvine32.lib
includelib	/irvine/user32.lib
includelib	/irvine/kernel32.lib

.data
;String intPrompt = "Please enter an integer value: "
intPrompt byte	"Please enter and integer value: ",0

;String hexPrompt = "Please enter a hex value: "
hexPrompt	byte	"Please enter a hex value: ",0

;String error = "Value too large for multiplication"
error	byte	"Value is too large for multiplication",0

.code
main PROC
	sub		esp,32
	;[ebp-4] == a
	;[ebp-8] == b
	;[ebp-12] == result
	;[ebp-16] == power
	;[ebp-20] == c
	;[ebp-24] == x
	;[ebp-28] == y
	;[ebp-32] == m

;Display(intPrompt)
	mov		edx,OFFSET intPrompt
	call		WriteString
;unsigned int a = ReadInt()
	call		ReadDec
	mov		[ebp-4],eax

;Display(hexPrompt)
	mov		edx,OFFSET hexPrompt
	call		WriteString
;unsigned int b = ReadHex()
	call		ReadDec
	mov		[ebp-8],eax

;unsigned int result = factorial(a)
	push		[ebp-4]
	call		factorial
	mov		[ebp-12],eax
;Display(result)
	call		WriteDec
	call		crlf

;unsigned int power = signedPower( a, b )
	push		[ebp-8]
	push		[ebp-4]
	call		signedPower
	mov		[ebp-16],eax
;Display(power)
	call		WriteDec
	call		crlf

;int c = ReadInt()
	call		ReadInt
	mov		[ebp-20],eax

;int16 x = half_of_c 
	mov		[ebp-24],ax
	mov		eax,[ebp-24]

;int8 y = low_quater_of_c
	mov		[ebp-28],al

;int m = magic( x, y )
	push		[ebp-28]
	push		[ebp-24]
	call		magic
	mov		[ebp-32],eax
;Display( m );
	call		WriteInt
	call		crlf
	add		esp,32
	exit
main ENDP

;The procedure takes in a value "n" and returns n! (n factorial)
;	Receives:
;		a:PTR DWORD
;	Returns:
;		eax
factorial PROC
	push		ebp
	mov		ebp,esp
	push		ebx
	mov		eax,[ebp+8]
	cmp		eax,0
;else
	ja		NotDone
;if ( n <= 1 )
;return n;
	mov		eax,1
	jmp		Done
;return n * factorial( n - 1);
NotDone:	
	dec		eax
	push		eax
	call		factorial

Recursive:
	mov		ebx,[ebp+8]
	mul		ebx

Done:
	pop		ebx
	pop		ebp
	ret		4

factorial ENDP

;The procedure takes in a "base" and an "exponent" and calculated the base raised to the exponent
;	Receives:
;		a:PTR DWORD
;		b:PTR DWORD
;	Returns:
;		eax
signedPower PROC
	push		ebp
	mov		ebp,esp
	push		ecx
;unsigned int result = 1
	mov		eax,1
	mov		ecx,[ebp+12]
	cmp		ecx,0
	jna		Done
WhileLoop:
	mul		DWORD PTR [ebp+8]
	jnc		LoopEnd
;Display(error)
	mov		edx,OFFSET error
	call		WriteString
;return -1 // (0xFFFFFFFF)
	mov		eax,-1
	pop		ecx
	pop		ebp
	ret		8

LoopEnd:
	loop		WhileLoop

Done:
	pop		ecx
	pop		ebp
	ret		8
signedPower ENDP

;The procedure does some magical things
;	Receives:
;		x:PTR WORD
;		y:PTR BYTE
;	Returns:
;		eax
magic PROC
	push		ebp
	mov		ebp,esp
	sub		esp,12		;Making room for local variables
	;[ebp-4] == c
	;[ebp-8] == div
	;[ebp-12] == mod
	
;int16 c = b 
	movsx	ax,BYTE PTR [ebp+12]
	mov		[ebp-4],ax
	mov		ax,[ebp+8]
	cwd
	mov		bx,[ebp-4]
	idiv		bx
;int16 div = a / c
	mov		[ebp-8],al
;int16 mod = a % c
	mov		[ebp-12],ah
	movsx	eax,WORD PTR [ebp-8]
;result = result >> div
	mov		ecx,[ebp-8]
Shift:
	sar		eax,1
	loop		Shift

	;return result
	add		esp,12
	pop		ebp
	ret		8
magic ENDP
END main

