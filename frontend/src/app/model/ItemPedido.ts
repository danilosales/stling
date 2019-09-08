export class ItemPedido {
  produtoId: number;
  produtoDescricao: string;
  quantidade = 0;
  produtoValor = 0.0;

  get totalItem(): number {
    return this.produtoValor * this.quantidade;
  }
}
