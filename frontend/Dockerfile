FROM mhart/alpine-node:12 AS builder
WORKDIR /app
COPY . .
RUN yarn run build

FROM mhart/alpine-node
RUN yarn global add serve
WORKDIR /app
COPY --from=builder /app/dist/frontend .
CMD ["serve", "-p", "4200", "-s", "."]
