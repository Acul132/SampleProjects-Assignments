; Question03 (question3.asm)
;Authors: Joshua Renelli && Paolo Scola 

.386
.model flat,stdcall
.stack	4096
ExitProcess PROTO, dwExitCode:DWORD

INCLUDE /irvine/irvine32.inc
includelib	/irvine/irvine32.lib
includelib	/irvine/user32.lib
includelib	/irvine/kernel32.lib

.data
	;int x, y, z
x DWORD ?
y DWORD ?
z DWORD ? 
prompt BYTE "Please enter and integer value: ",0

.code
main PROC
	;x = PromptAndGetInt
	call PromptAndGetInt
	mov		x,eax

	;y = PromptAndGetInt
	call PromptAndGetInt
	mov		y,eax

	;for ( c = 1; c < x; ++c )
	mov		ecx,1
	for_outer:
	cmp		ecx,x
	je		done
	mov		ebx,0

	;z = 0
	mov		z,0

	;for ( d = 0; d < y; ++d )
	for_inner:
	;<change background colour to value of RandomInt(from 0 to 255)>
	mov		eax,256
	call		RandomRange
	shl		ax,4
	;<change font colour to value of z>
	add		eax,z
	call		SetTextColor

	;z += c;
	add		z,ecx
	mov		eax,500
	;<clear screen>
	call		clrscr
	;<pause 0.5 seconds>
	call		Delay

	cmp		ebx,y
	je		for_outer_end
	inc		ebx
	jmp		for_inner

	for_outer_end:

	inc		ecx
	jmp		for_outer

	done: 
	;<change background colour to black, and text to light grey>
	xor		eax,eax
	add		ax,7
	call		SetTextColor
	exit
main ENDP

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