package maki

import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

object Maki extends App {
  def parse(source: String): String = {
    val charStream = CharStreams.fromString(source)
    val lexer = new MakiLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new MakiParser(tokens)
    val tree = parser.kotlinFile

    val listener = new MakiListenerApp
    val walker = new ParseTreeWalker
    walker.walk(listener, tree)
    tree.toStringTree(parser)
  }
  println(parse(args(0)))
}