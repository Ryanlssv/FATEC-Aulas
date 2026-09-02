using System;
using System.Collections.Generic;
using System.Text;

namespace MauiAppAula4.Mod
{
    public class DoisParametro
    {
        // Atributos
        private int aN1 = 0;
        private int aN2 = 0;
        private int aS = 0;

        // Método construtor
        public DoisParametro() {

            this.aN1 = 0;
            this.aN2 = 0;
            this.aS = 0;
        }

        // Métodos de acesso
        public int gsN1 { get => aN1; set => aN1 = value; }
        public int gsN2 { get => aN2; set => aN2 = value; }
        public int gsS { get => aS; set => aS = value; }

        // Métodos específicos
        public double CalcularSoma()
        {
            gsS = gsN1 + gsN2;
            return gsS;
        }

        public double CalcularSub()
        {
            gsS = gsN1 - gsN2;
            return gsS;
        }

        public double CalcularMult()
        {
            gsS = gsN1 * gsN2;
            return gsS;
        }

        public double CalcularDiv()
        {   

            if(gsN2 == 0)
            {
                aS = 0;
                return aS;
            }
            else if (gsN1 == 0)
            {
                aS = 0;
                return aS;
            }
            gsS = gsN1 / gsN2;
            return gsS;
        }


    }
}
