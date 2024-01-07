import { Directive, ElementRef, Input, Renderer2 } from '@angular/core';

@Directive({
    selector: '[setColor]',
    standalone: true
})
export class SetColorDirective {
  @Input("setColor")
  set setColor(value: number) {
    if(value >2){
      this.renderer.setStyle(this.el.nativeElement, 'background-color', 'green');
    }else if(value <1){
      this.renderer.setStyle(this.el.nativeElement, 'background-color', 'yellow');
    }else if(value === 1){
      this.renderer.setStyle(this.el.nativeElement, 'background-color', 'red');
    }else{
      this.renderer.setStyle(this.el.nativeElement, 'background-color', 'white');
    }
  }

  constructor(private el: ElementRef, private renderer: Renderer2) {}
}