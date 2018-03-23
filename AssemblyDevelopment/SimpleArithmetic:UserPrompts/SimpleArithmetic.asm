; Question02 (question2.asm)
; Authors: Joshua Renelli && Paolo Scola 

.386
.model flat,stdcall
.stack	4096
ExitProcess PROTO, dwExitCode:DWORD

INCLUDE /irvine/irvine32.inc
includelib	/irvine/irvine32.lib
includelib	/irvine/user32.lib
includelib	/irvine/kernel32.lib

.data
	;int x = 10011010 (binary), y = A3 (hex), z = 13 (octal)
x DWORD 10011010b
y DWORD 0A3h
z DWORD 13o 

.code
main PROC
;z = x + y
	mov		eax,x
	add		eax,y
	mov		z,eax
;z++
	inc		z
;x = z;
	mov		eax,z
	mov		x,eax
;y = z - x
	mov		eax,z
	sub		eax,x
	mov		y,eax
;<DumpRegs>
	call		DumpRegs

	exit
main ENDP
END main